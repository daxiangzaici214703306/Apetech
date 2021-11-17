package com.hsns.network.compoent;

import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;

/**
 * Created by Administrator on 2020/5/20.
 *
 * @Email 
 * @desc
 */
public interface IClientComponent {

    public abstract int connectTimeOutSeconds();

    public abstract int readTimeOutSeconds();

    public abstract int writeTimeOutSeconds();

    public abstract Cache cache();

    public abstract List<Interceptor> interceptors();

    public abstract List<Interceptor> networkInterceptors();
}
