package com.cdc.android.json;
import android.app.Application;
import com.cdc.crash.CrashConfig;
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashConfig.Builder.create().apply();
    }
}
