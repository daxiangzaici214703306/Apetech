package com.hsns.picture;

import android.os.Handler;

import com.hsns.base.utils.UiUtils;
import com.hsns.base.view.BaseActivity;
import com.hsns.picture.main.view.MainActivity;

public class SplashActivity extends BaseActivity implements Runnable {
    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mHandler.postDelayed(this, 2000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(this);
        mHandler = null;
    }

    @Override
    public void run() {
        UiUtils.transActivity(SplashActivity.this, MainActivity.class);
        finish();
    }
}
