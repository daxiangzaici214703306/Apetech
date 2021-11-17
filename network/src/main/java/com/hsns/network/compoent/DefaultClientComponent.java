package com.hsns.network.compoent;

import android.content.Context;

import com.hsns.network.base.BaseClientComponent;

import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2020/5/21.
 *
 * @Email 
 * @desc
 */
public class DefaultClientComponent extends BaseClientComponent {

    public DefaultClientComponent(Context context) {
        super(context);
    }

    @Override
    protected boolean isCacheRequired() {
        return false;
    }

    @Override
    protected List<Interceptor> customInterceptors() {
        return null;
    }

    @Override
    protected int maxRetryCount() {
        return 1;
    }

    @Override
    public List<Interceptor> interceptors() {
        return null;
    }
}
