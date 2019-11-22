package com.cdc.android.log;

import android.app.Application;

import com.cdc.crash.CrashConfig;
import com.cdc.mlog.MLog;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CrashConfig.Builder.create().apply();
        MLog.init(this);

    }
}
