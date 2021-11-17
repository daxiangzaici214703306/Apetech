package com.hsns.picture.webview;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.hsns.base.listener.PageChangeListener;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.base.view.BaseFragment;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentWebviewBinding;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;


public class WebViewFragment extends BaseFragment implements PageChangeListener {
    private FragmentWebviewBinding fragmentWebviewBinding;
    private String url;
    private static final String TAG = "WebViewFragment";
    private AgentWeb mAgentWeb;
    private WebView mWebView;
    private String tempUrl;//判断是否url有变化

    @Override
    public int getLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        Log.d(TAG, "initView");
        LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        fragmentWebviewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        fragmentWebviewBinding.back.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        webViewSetting();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:
                UiUtils.transFragment(PictureApplication.getApplication(), BaseUtils.TAG_BACK, MainActivity.class);
                break;
        }
    }

    /**
     * websetting设置
     */
    private void webViewSetting() {
        if (getActivity() != null) {
            initAgentWeb();
            initWebSetting();
        }
    }


    /**
     * 初始化AgentWeb
     */
    private void initAgentWeb() {
        mAgentWeb = AgentWeb.with(getActivity())
                .setAgentWebParent(fragmentWebviewBinding.webviewLayout, 0, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(-1, 3)
                .setWebViewClient(new CustomWebClient())
                .setWebChromeClient(new WebChromeClient())////WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url);
    }

    /**
     * 初始化websetting
     */
    private void initWebSetting() {
        mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings settings = mWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }


    @Override
    public View getResultView() {
        return fragmentWebviewBinding.getRoot();
    }


    @Override
    public void onPageChange(String url) {
        Log.d(TAG, "onPageChange url==>" + url + " activity==>" + getActivity());
        this.url = url;
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause ");
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume ");
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged hidden==>" + hidden + " url==>" + url + " tempUrl==>" + tempUrl);
        String blankUrl = "about:blank";
        doLoadUrlOperation(!hidden ? url : blankUrl);
        tempUrl = url;
    }

    /**
     * 加载对应的url
     *
     * @param url 链接
     */
    private void doLoadUrlOperation(String url) {
        if (mWebView != null && !TextUtils.isEmpty(url) && tempUrl != url) {
            mWebView.loadUrl(url);
        }
    }
}
