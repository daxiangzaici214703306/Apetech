package com.hsns.network.constant;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2020/5/21.
 *
 * @Email 
 * @desc
 */
public class DefaultHttpConfig {
    /**
     * 超时时间 20s
     */
    public static final int DEFAULT_CONNECT_TIME_OUT = 15;
    /**
     * 读取超时时间10s
     */
    public static final int DEFAULT_READ_TIME_OUT = 20;

    /**
     * 写超时时间10s
     */
    public static final int DEFAULT_WRITE_TIME_OUT = 20;

    /**
     * 缓存大小
     */
    public static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;

    //缓存目录
    public static final File getDefaultHttpCacheDir(Context context) {
        return new File(context.getCacheDir(), "OkHttpCache");
    }
}
