package com.hsns.picture.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//CustomWebClient主要帮助WebView处理各种通知、请求事件
public class CustomWebClient extends WebViewClient {
    private static final String TAG = "CustomWebClient";

    @Override
    public void onPageFinished(WebView view, String url) {//页面加载完成
        Log.d(TAG, "onPageFinished ");
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
        Log.d(TAG, "onPageStarted ");
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest resourceRequest) {
        String url = resourceRequest.getUrl().toString();
        Log.d(TAG, "拦截url:" + url);
        if (url.equals("http://www.google.com/")) {
//                Toast.makeText(MainActivity.this,"国内不能访问google,拦截该url",Toast.LENGTH_LONG).show();
            return true;//表示我已经处理过了
        }
        return false;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
        // super.onReceivedSslError(view, handler, error);

        // 接受所有网站的证书，忽略SSL错误，执行访问网页
        handler.proceed();
    }
}
