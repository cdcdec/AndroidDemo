package com.cdc.template.app;

import android.app.Application;
import me.jessyan.autosize.AutoSizeConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AutoSizeConfig.getInstance();
    }
}
