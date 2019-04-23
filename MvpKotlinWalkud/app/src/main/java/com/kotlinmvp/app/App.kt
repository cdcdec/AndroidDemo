package com.kotlinmvp.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary
import com.kotlinmvp.app.utils.ContextUtil
import com.kotlinmvp.app.utils.DisplayManager
import com.kotlinmvp.app.utils.MLog

/**
 * Application
 * Created by Zhuliya on 2018/11/7
 */
class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this) //分包处理，解决64k问题
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        ContextUtil.setContext(this)

        MLog.init()//初始化日志
        DisplayManager.init(this)//初始化UI显示工具类
    }


}