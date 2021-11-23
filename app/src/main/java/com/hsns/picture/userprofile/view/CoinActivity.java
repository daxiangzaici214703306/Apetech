package com.hsns.picture.userprofile.view;

import android.text.TextUtils;

import androidx.databinding.DataBindingUtil;

import com.hsns.base.utils.BaseUtils;
import com.hsns.base.view.BaseActivity;
import com.hsns.picture.R;
import com.hsns.picture.databinding.ActivityCoinBinding;

public class CoinActivity extends BaseActivity {
    private ActivityCoinBinding binding;
    private CoinRankFragment mCoinRankFragment;
    private PerCoinFragment mPerCoinFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coin;
    }

    @Override
    public void initView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initFragment();
    }

    private void initFragment() {
        mCoinRankFragment = new CoinRankFragment();
        mPerCoinFragment = new PerCoinFragment();
        tranFragmentByTag();
    }

    private void tranFragmentByTag() {
        switch (tag) {
            case BaseUtils.TAG_HOME:
            case BaseUtils.TAG_COINRANKINFO:
                showFragment(R.id.coin_content, mCoinRankFragment, BaseUtils.TAG_COINRANKINFO);
                break;
            case BaseUtils.TAG_PERCOININFO:
                showFragment(R.id.coin_content, mPerCoinFragment, BaseUtils.TAG_PERCOININFO);
                break;
        }
    }
}
