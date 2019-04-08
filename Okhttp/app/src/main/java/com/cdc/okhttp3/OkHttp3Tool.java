package com.cdc.okhttp3;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttp3Tool {
    public OkHttpClient initOkHttpClient(Context context) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d("TTT", message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                .addNetworkInterceptor(logging);
        return builder.build();
    }

}
