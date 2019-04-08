package com.cdc.okhttp3;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class HttpCacheInterceptor implements Interceptor{

    private Context context;

    public HttpCacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request()).newBuilder()
                //.request(newRequest)
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + 1)
                .build();
    }
}