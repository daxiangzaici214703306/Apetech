package com.hsns.base.listener;

public interface PageChangeListener {
    /**
     * webview加载url的监听
     * @param url 链接
     */
    void onPageChange(String url);
}
