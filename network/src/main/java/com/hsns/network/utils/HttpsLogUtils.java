package com.hsns.network.utils;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpsLogUtils {
    private static boolean isDebug = true;

    public static HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor;
        interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("=====log======", message);
            }
        }).setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }
}
