package com.hsns.network.provider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.util.Log;

import com.hsns.network.cert.TrustAllCerts;
import com.hsns.network.cert.TrustAllHostnameVerifier;
import com.hsns.network.interceptor.HeaderInterceptor;
import com.hsns.network.compoent.IClientComponent;
import com.hsns.network.interceptor.SaveCookieInterceptor;
import com.hsns.network.utils.HttpsLogUtils;
import com.hsns.network.utils.HttpsUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Administrator on 2020/5/21.
 *
 * @Email
 * @desc
 */
public class OkHttpClientProvider {

    private static final String TAG = OkHttpClientProvider.class.getSimpleName();
    private static OkHttpClient.Builder builder;

    public static OkHttpClient provideOkHttpClient(Context context, IClientComponent clientComponent) {
        builder = new OkHttpClient.Builder();
        builder.addInterceptor(HttpsLogUtils.getInterceptor());
        builder.addInterceptor(new HeaderInterceptor(context));
        builder.addInterceptor(new SaveCookieInterceptor(context));
        timeOutSetting(clientComponent);
        addVerifier();
        addCustomizeInterceptors(clientComponent);
        builder.cache(clientComponent.cache());
        OkHttpClient client = builder.build();
        connectListen(client, context);
        return client;
    }

    /**
     * 连接监听
     *
     * @param client  客户端对象
     * @param context 上下文
     */
    private static void connectListen(final OkHttpClient client, Context context) {
        // 网络断开清空连接池
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                Log.i(TAG, "NetworkCallback -> onAvailable");
                client.connectionPool().evictAll();
            }

            @Override
            public void onLost(Network network) {
                super.onLost(network);
                Log.i(TAG, "NetworkCallback -> onLost");
                client.connectionPool().evictAll();
            }
        });

    }

    /**
     * 设置超时时间
     */
    private static void timeOutSetting(IClientComponent clientComponent) {
        builder.connectTimeout(clientComponent.connectTimeOutSeconds(), TimeUnit.SECONDS);
        builder.readTimeout(clientComponent.readTimeOutSeconds(), TimeUnit.SECONDS);
        builder.writeTimeout(clientComponent.writeTimeOutSeconds(), TimeUnit.SECONDS);
    }

    /**
     * 添加TLS证书并认证
     */
    private static void addVerifier() {
        SSLSocketFactory sslSocketFactory = HttpsUtils.getSSLSocketFactory();
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory, new TrustAllCerts());//创建一个证书对象
        }
        builder.hostnameVerifier(new TrustAllHostnameVerifier());
    }

    /**
     * 添加自定义的拦截器
     *
     * @param clientComponent 拦截器列表对象
     */
    private static void addCustomizeInterceptors(IClientComponent clientComponent) {
        if (clientComponent.interceptors() != null) {
            for (Interceptor interceptor : clientComponent.interceptors()) {
                builder.addInterceptor(interceptor);
            }
        }
        if (clientComponent.networkInterceptors() != null) {
            for (Interceptor interceptor : clientComponent.networkInterceptors()) {
                builder.addNetworkInterceptor(interceptor);
            }
        }
    }
}
