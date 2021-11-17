package com.hsns.network.interceptor;

import android.content.Context;
import android.text.TextUtils;

import com.hsns.base.utils.SharePreUtils;
import com.hsns.network.constant.RetrofitConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private Context mContext;

    public HeaderInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    public Response intercept(Interceptor.Chain chain) {
        Response mResponse = null;
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        builder.addHeader("Content-type", "application/json; charset=utf-8");
        // .header("token", token)
        // .method(request.method(), request.body())

//        String domain = request.url().host();
        String url = request.url().toString();
        if ((url.contains(RetrofitConstants.COLLECTIONS_WEBSITE)   //!TextUtils.isEmpty(domain) &&
                || url.contains(RetrofitConstants.UNCOLLECTIONS_WEBSITE)
                || url.contains(RetrofitConstants.ARTICLE_WEBSITE)
                || url.contains(RetrofitConstants.TODO_WEBSITE)
                || url.contains(RetrofitConstants.COIN_WEBSITE)
                || url.contains(RetrofitConstants.USERINFO_WEBSITE))) {
            String spDomain = SharePreUtils.getCookie(mContext);
            String cookie = !TextUtils.isEmpty(spDomain) ? spDomain : "";
            if (!TextUtils.isEmpty(cookie)) {
                // 将 Cookie 添加到请求头
                builder.addHeader(RetrofitConstants.COOKIE_NAME, cookie);
            }
        }
        try {
            mResponse = chain.proceed(builder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResponse;
    }
}
