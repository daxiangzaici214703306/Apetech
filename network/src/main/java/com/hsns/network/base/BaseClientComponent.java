package com.hsns.network.base;

import android.content.Context;


import com.hsns.network.compoent.IClientComponent;
import com.hsns.network.constant.DefaultHttpConfig;

import java.util.List;
import okhttp3.Cache;
import okhttp3.Interceptor;

/**
 * Created by Administrator on 2020/5/21.
 *
 * @Email
 * @desc
 */
public abstract class BaseClientComponent implements IClientComponent {
    private Context mContext;

    public BaseClientComponent(Context context) {
        mContext = context;
    }

    @Override
    public int connectTimeOutSeconds() {
        return DefaultHttpConfig.DEFAULT_CONNECT_TIME_OUT;
    }

    @Override
    public int readTimeOutSeconds() {
        return DefaultHttpConfig.DEFAULT_READ_TIME_OUT;
    }

    @Override
    public int writeTimeOutSeconds() {
        return DefaultHttpConfig.DEFAULT_WRITE_TIME_OUT;
    }

    @Override
    public Cache cache() {
        if (isCacheRequired()) {
            return new Cache(DefaultHttpConfig.getDefaultHttpCacheDir(mContext), DefaultHttpConfig.DEFAULT_CACHE_SIZE);
        } else {
            return null;
        }
    }



    @Override
    public List<Interceptor> networkInterceptors() {
        return null;
    }

    /**
     * 是否需要缓存
     */
    protected abstract boolean isCacheRequired();

    /**
     * 自定义的拦截器
     */
    protected abstract List<Interceptor> customInterceptors();

    /**
     * 最大重试次数.
     *
     * @return 大于0代表重试次数，小于等于0代表不需要重试。
     */
    protected abstract int maxRetryCount();
}
