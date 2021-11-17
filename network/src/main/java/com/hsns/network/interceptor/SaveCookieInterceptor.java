package com.hsns.network.interceptor;

import android.content.Context;

import com.hsns.network.constant.RetrofitConstants;
import com.hsns.network.utils.HttpsUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SaveCookieInterceptor implements Interceptor {
    private Context mContext;

    public SaveCookieInterceptor(Context mContext) {
        this.mContext=mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String requestUrl = request.url().toString();
//        String domain = request.url().host();
        // set-cookie maybe has multi, login to save cookie
        if ((requestUrl.contains(RetrofitConstants.SAVE_USER_LOGIN_KEY)
                || requestUrl.contains(RetrofitConstants.SAVE_USER_REGISTER_KEY))
                && !response.headers(RetrofitConstants.SET_COOKIE_KEY).isEmpty()) {
            List<String> cookies=response.headers(RetrofitConstants.SET_COOKIE_KEY);
            HttpsUtils.saveCookie(mContext,cookies);
        }
        return response;
    }
}
