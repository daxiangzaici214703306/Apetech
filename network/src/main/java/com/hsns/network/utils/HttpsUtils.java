package com.hsns.network.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hsns.base.utils.SharePreUtils;
import com.hsns.network.cert.TrustAllCerts;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 跳过SSL验证辅助类
 *
 * @author Aaron
 * @since 2021/3/18
 */
public class HttpsUtils {
    private static final String TAG = "HttpsUtils";
    private static TrustManager[] trustManagers;

    /**
     * 获取这个SSLSocketFactory
     *
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, getTrustManager(), new SecureRandom());
            sslContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e(TAG, "getSSLSocketFactory: e", e);
        }
        return null;
    }

    /**
     * 获取TrustManager
     */
    public static TrustManager[] getTrustManager() {
        return new TrustManager[]{new CustomTrustManager()};
    }

    public static HostnameVerifier getHostnameVerifier() {
        return (s, sslSession) -> {
            Log.i(TAG, "verify: No blank lines");
            return s != null && sslSession != null;
        };
    }

    /**
     * 解析响应得到的cookie列表，合成请求的cookies
     *
     * @param cookies
     * @return
     */
    public static void saveCookie(Context mContext, List<String> cookies) {
        if (cookies == null) return;
        final StringBuilder cookieBuilder = new StringBuilder();
        for (int i = 0; i < cookies.size(); i++) {
            cookieBuilder.append(cookies.get(i));
            if (i != cookies.size() - 1) {
                cookieBuilder.append(";");
            }
        }
        String cookie = cookieBuilder.toString();
        if (TextUtils.isEmpty(cookie)) return;
        SharePreUtils.storeCookie(mContext, cookie);
    }


    public static class CustomTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Log.i(TAG, "checkClientTrusted: No blank lines");
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Log.i(TAG, "checkServerTrusted: No blank lines");
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}
