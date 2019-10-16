package com.cdc.crash;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CustomActivityOnCrash {
    private final static String TAG = "CustomActivityOnCrash";
    //Extras passed to the error activity
    private static final String EXTRA_CONFIG = "com.cdc.crash.EXTRA_CONFIG";
    private static final String EXTRA_STACK_TRACE = "com.cdc.crash.EXTRA_STACK_TRACE";
    private static final String EXTRA_ACTIVITY_LOG = "com.cdc.crash.EXTRA_ACTIVITY_LOG";
    //General constants
    private static final String INTENT_ACTION_ERROR_ACTIVITY = "com.cdc.crash.ERROR";
    private static final String INTENT_ACTION_RESTART_ACTIVITY = "com.cdc.crash.RESTART";
    private static final String CORE_HANDLER_PACKAGE_NAME = "com.cdc.crash";
    private static final String DEFAULT_HANDLER_PACKAGE_NAME = "com.android.internal.os";
    private static final int MAX_STACK_TRACE_SIZE = 131071; //128 KB - 1
    private static final int MAX_ACTIVITIES_IN_LOG = 50;
    //Shared preferences
    private static final String SHARED_PREFERENCES_FILE = "custom_activity_on_crash";
    private static final String SHARED_PREFERENCES_FIELD_TIMESTAMP = "last_crash_timestamp";
    private static Application application;
    private static CrashConfig config = new CrashConfig();
    private static final Deque<String> activityLog = new ArrayDeque<>(MAX_ACTIVITIES_IN_LOG);
    private static WeakReference<Activity> lastActivityCreated = new WeakReference<>(null);
    private static boolean isInBackground = true;


    public static void install(@Nullable final Context context) {
        try {
            if (context == null) {
                Log.e(TAG, "Install failed: context is null!");
            } else {
                final Thread.UncaughtExceptionHandler oldHandler = Thread.getDefaultUncaughtExceptionHandler();
                if (oldHandler != null && oldHandler.getClass().getName().startsWith(CORE_HANDLER_PACKAGE_NAME)) {
                    Log.e(TAG, "CustomActivityOnCrash was already installed, doing nothing!");
                } else {
                    if (oldHandler != null && !oldHandler.getClass().getName().startsWith(DEFAULT_HANDLER_PACKAGE_NAME)) {
                        Log.e(TAG, "IMPORTANT WARNING! You already have an UncaughtExceptionHandler, are you sure this is correct? If you use a custom UncaughtExceptionHandler, you must initialize it AFTER CustomActivityOnCrash! Installing anyway, but your original handler will not be called.");
                    }

                    application = (Application) context.getApplicationContext();

                    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread thread, final Throwable throwable) {
                            Log.e(TAG, "11111App has crashed, executing CustomActivityOnCrash's UncaughtExceptionHandler");
                            if (config.isEnabled()) {
                                Log.e(TAG, "App has crashed, executing CustomActivityOnCrash's UncaughtExceptionHandler", throwable);


                                saveCrash(application,throwable);

                                if (hasCrashedInTheLastSeconds(application)) {
                                    Log.e(TAG, "App already crashed recently, not starting custom error activity because we could enter a restart loop. Are you sure that your app does not crash directly on init?", throwable);
                                    if (oldHandler != null) {
                                        oldHandler.uncaughtException(thread, throwable);
                                        return;
                                    }
                                } else {
                                    setLastCrashTimestamp(application, new Date().getTime());

                                    Class<? extends Activity> errorActivityClass = config.getErrorActivityClass();

                                    if (errorActivityClass == null) {
                                        errorActivityClass = guessErrorActivityClass(application);
                                    }

                                    if (isStackTraceLikelyConflictive(throwable, errorActivityClass)) {
                                        Log.e(TAG, "Your application class or your error activity have crashed, the custom activity will not be launched!");
                                        if (oldHandler != null) {
                                            oldHandler.uncaughtException(thread, throwable);
                                            return;
                                        }
                                    } else if (config.getBackgroundMode() == BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM || !isInBackground) {

                                        final Intent intent = new Intent(application, errorActivityClass);
                                        StringWriter sw = new StringWriter();
                                        PrintWriter pw = new PrintWriter(sw);
                                        throwable.printStackTrace(pw);
                                        String stackTraceString = sw.toString();
                                        Log.e(TAG, "=======" + stackTraceString);
                                        //Reduce data to 128KB so we don't get a TransactionTooLargeException when sending the intent.
                                        //The limit is 1MB on Android but some devices seem to have it lower.
                                        if (stackTraceString.length() > MAX_STACK_TRACE_SIZE) {
                                            String disclaimer = " [stack trace too large]";
                                            stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length()) + disclaimer;

                                            Log.e(TAG, "1=======" + stackTraceString);
                                        }
                                        intent.putExtra(EXTRA_STACK_TRACE, stackTraceString);

                                        if (config.isTrackActivities()) {
                                            StringBuilder activityLogStringBuilder = new StringBuilder();
                                            while (!activityLog.isEmpty()) {
                                                activityLogStringBuilder.append(activityLog.poll());
                                            }
                                            intent.putExtra(EXTRA_ACTIVITY_LOG, activityLogStringBuilder.toString());
                                            Log.e(TAG, "2=======" + activityLogStringBuilder.toString());
                                        }

                                        if (config.isShowRestartButton() && config.getRestartActivityClass() == null) {
                                            //We can set the restartActivityClass because the app will terminate right now,
                                            //and when relaunched, will be null again by default.
                                            config.setRestartActivityClass(guessRestartActivityClass(application));
                                        }

                                        intent.putExtra(EXTRA_CONFIG, config);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        if (config.getEventListener() != null) {
                                            config.getEventListener().onLaunchErrorActivity();
                                        }


                                        application.startActivity(intent);
                                    } else if (config.getBackgroundMode() == BackgroundMode.BACKGROUND_MODE_CRASH) {
                                        if (oldHandler != null) {
                                            oldHandler.uncaughtException(thread, throwable);
                                            return;
                                        }
                                        //If it is null (should not be), we let it continue and kill the process or it will be stuck
                                    }
                                    //Else (BACKGROUND_MODE_SILENT): do nothing and let the following code kill the process
                                }
                                final Activity lastActivity = lastActivityCreated.get();
                                if (lastActivity != null) {
                                    //We finish the activity, this solves a bug which causes infinite recursion.
                                    //See: https://github.com/ACRA/acra/issues/42
                                    lastActivity.finish();
                                    lastActivityCreated.clear();
                                }
                                killCurrentProcess();
                            } else if (oldHandler != null) {
                                oldHandler.uncaughtException(thread, throwable);
                            }
                        }
                    });


                }
            }

        } catch (Exception e) {

        }
    }


    private static void killCurrentProcess() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    private static Class<? extends Activity> guessRestartActivityClass(@NonNull Context context) {
        Class<? extends Activity> resolvedActivityClass;

        //If action is defined, use that
        resolvedActivityClass = getRestartActivityClassWithIntentFilter(context);

        //Else, get the default launcher activity
        if (resolvedActivityClass == null) {
            resolvedActivityClass = getLauncherActivity(context);
        }

        return resolvedActivityClass;
    }

    private static Class<? extends Activity> getLauncherActivity(@NonNull Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (intent != null && intent.getComponent() != null) {
            try {
                return (Class<? extends Activity>) Class.forName(intent.getComponent().getClassName());
            } catch (ClassNotFoundException e) {
                //Should not happen, print it to the log!
                Log.e(TAG, "Failed when resolving the restart activity class via getLaunchIntentForPackage, stack trace follows!", e);
            }
        }

        return null;
    }

    private static Class<? extends Activity> getRestartActivityClassWithIntentFilter(@NonNull Context context) {
        Intent searchedIntent = new Intent().setAction(INTENT_ACTION_RESTART_ACTIVITY).setPackage(context.getPackageName());
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(searchedIntent,
                PackageManager.GET_RESOLVED_FILTER);

        if (resolveInfos != null && resolveInfos.size() > 0) {
            ResolveInfo resolveInfo = resolveInfos.get(0);
            try {
                return (Class<? extends Activity>) Class.forName(resolveInfo.activityInfo.name);
            } catch (ClassNotFoundException e) {
                //Should not happen, print it to the log!
                Log.e(TAG, "Failed when resolving the restart activity class via intent filter, stack trace follows!", e);
            }
        }

        return null;
    }

    private static boolean isStackTraceLikelyConflictive(@NonNull Throwable throwable, @NonNull Class<? extends Activity> activityClass) {
        do {
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if ((element.getClassName().equals("android.app.ActivityThread") && element.getMethodName().equals("handleBindApplication")) || element.getClassName().equals(activityClass.getName())) {
                    return true;
                }
            }
        } while ((throwable = throwable.getCause()) != null);
        return false;
    }

    private static Class<? extends Activity> getErrorActivityClassWithIntentFilter(@NonNull Context context) {
        Intent searchedIntent = new Intent().setAction(INTENT_ACTION_ERROR_ACTIVITY).setPackage(context.getPackageName());
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(searchedIntent,
                PackageManager.GET_RESOLVED_FILTER);

        if (resolveInfos != null && resolveInfos.size() > 0) {
            ResolveInfo resolveInfo = resolveInfos.get(0);
            try {
                return (Class<? extends Activity>) Class.forName(resolveInfo.activityInfo.name);
            } catch (ClassNotFoundException e) {
                //Should not happen, print it to the log!
                Log.e(TAG, "Failed when resolving the error activity class via intent filter, stack trace follows!", e);
            }
        }

        return null;
    }


    private static Class<? extends Activity> guessErrorActivityClass(@NonNull Context context) {
        Class<? extends Activity> resolvedActivityClass;

        //If action is defined, use that
        resolvedActivityClass = getErrorActivityClassWithIntentFilter(context);

        //Else, get the default error activity
        if (resolvedActivityClass == null) {
            resolvedActivityClass = DefaultErrorActivity.class;
        }

        return resolvedActivityClass;
    }


    public static CrashConfig getConfig() {
        return config;
    }

    /**
     * 该注解用于想要指定访问元素权限的范围
     *
     * @param config the configuration to use
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static void setConfig(@NonNull CrashConfig config) {
        CustomActivityOnCrash.config = config;
    }

    private static boolean hasCrashedInTheLastSeconds(@NonNull Context context) {
        long lastTimestamp = getLastCrashTimestamp(context);
        long currentTimestamp = new Date().getTime();
        return (lastTimestamp <= currentTimestamp && currentTimestamp - lastTimestamp < config.getMinTimeBetweenCrashesMs());
    }

    private static void setLastCrashTimestamp(@NonNull Context context, long timestamp) {
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE).edit().putLong(SHARED_PREFERENCES_FIELD_TIMESTAMP, timestamp).apply();
    }

    /**
     * 记录上次crash的时间
     *
     * @param context
     * @return
     */
    private static long getLastCrashTimestamp(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE).getLong(SHARED_PREFERENCES_FIELD_TIMESTAMP, -1);
    }


    public static void restartApplicationWithIntent(@NonNull Activity activity, @NonNull Intent intent, @NonNull CrashConfig config) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        if (intent.getComponent() != null) {
            //If the class name has been set, we force it to simulate a Launcher launch.
            //If we don't do this, if you restart from the error activity, then press home,
            //and then launch the activity from the launcher, the main activity appears twice on the backstack.
            //This will most likely not have any detrimental effect because if you set the Intent component,
            //if will always be launched regardless of the actions specified here.
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
        }
        if (config.getEventListener() != null) {
            config.getEventListener().onRestartAppFromErrorActivity();
        }
        activity.finish();
        activity.startActivity(intent);
        killCurrentProcess();
    }

    public static void restartApplication(@NonNull Activity activity, @NonNull CrashConfig config) {
        Intent intent = new Intent(activity, config.getRestartActivityClass());
        restartApplicationWithIntent(activity, intent, config);
    }

    public static void closeApplication(@NonNull Activity activity, @NonNull CrashConfig config) {
        if (config.getEventListener() != null) {
            config.getEventListener().onCloseAppFromErrorActivity();
        }
        activity.finish();
        killCurrentProcess();
    }

    public static CrashConfig getConfigFromIntent(@NonNull Intent intent) {
        CrashConfig config = (CrashConfig) intent.getSerializableExtra(CustomActivityOnCrash.EXTRA_CONFIG);
        if (config.isLogErrorOnRestart()) {
            String stackTrace = getStackTraceFromIntent(intent);
            if (stackTrace != null) {
                Log.e(TAG, "The previous app process crashed. This is the stack trace of the crash:\n" + getStackTraceFromIntent(intent));
            }
        }

        return config;
    }


    public static String getStackTraceFromIntent(@NonNull Intent intent) {
        return intent.getStringExtra(CustomActivityOnCrash.EXTRA_STACK_TRACE);
    }

    private static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private static String getSdkVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    //21
    private static String getApiVersion() {
        return Build.VERSION.SDK_INT + "";
    }


    public static String getAllErrorDetailsFromIntent(@NonNull Context context, @NonNull Intent intent) {
        //I don't think that this needs localization because it's a development string...

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        //Get build date
        String buildDateAsString = getBuildDateAsString(context, dateFormat);

        //Get app version
        String versionName = getVersionName(context);

        String errorDetails = "";

        errorDetails += "Build version: " + versionName + " \n";
        if (buildDateAsString != null) {
            errorDetails += "Build date: " + buildDateAsString + " \n";
        }
        errorDetails += "Current date: " + dateFormat.format(currentDate) + " \n";
        //Added a space between line feeds to fix #18.
        //Ideally, we should not use this method at all... It is only formatted this way because of coupling with the default error activity.
        //We should move it to a method that returns a bean, and let anyone format it as they wish.
        errorDetails += "Device: " + getDeviceModelName() + " \n";
        errorDetails += "Api version: " + getApiVersion() + " \n";
        errorDetails += "SDK version: " + getSdkVersion() + " \n \n";
        errorDetails += "Stack trace:  \n";
        errorDetails += getStackTraceFromIntent(intent);

        String activityLog = getActivityLogFromIntent(intent);

        if (activityLog != null) {
            errorDetails += "\nUser actions: \n";
            errorDetails += activityLog;
        }
        return errorDetails;
    }


    public static String getActivityLogFromIntent(@NonNull Intent intent) {
        return intent.getStringExtra(CustomActivityOnCrash.EXTRA_ACTIVITY_LOG);
    }

    private static String getDeviceModelName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(@Nullable String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    private static String getBuildDateAsString(@NonNull Context context, @NonNull DateFormat dateFormat) {
        long buildDate;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);

            //If this failed, try with the old zip method
            ZipEntry ze = zf.getEntry("classes.dex");
            buildDate = ze.getTime();


            zf.close();
        } catch (Exception e) {
            buildDate = 0;
        }

        if (buildDate > 312764400000L) {
            return dateFormat.format(new Date(buildDate));
        } else {
            return null;
        }
    }

    // 用来存储设备信息和异常信息
    private static Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");


    private static void saveCrash(Context mContext, Throwable ex) {
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfo2File(ex);
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public static void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }


    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private static String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }


}
