package com.hsns.picture.userprofile.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.hsns.base.bean.CoinRankInfo;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.home.adapter.CoinRankAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CoinRankFragment extends BaseCoinFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener,
        CoinRankAdapter.onItemClickListener, Runnable {
    private CoinRankAdapter mCoinRankAdapter;
    private List<CoinRankInfo.Datas> mCoinRankInfo;
    private int num = 1;//当前积分排名加载的页数
    private static final String TAG = "CoinRankFragment";


    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater, container);
        setCoinRankInfoDataListener();
        initAdapter();
        rySetting();

    }

    /**
     * RecyclerView的设置
     */
    private void rySetting() {
        fragmentCoinrankBinding.homeRy.setLinearLayout();
        fragmentCoinrankBinding.homeRy.setAdapter(mCoinRankAdapter);
        fragmentCoinrankBinding.homeRy.setFooterViewText(PictureApplication.getApplication().getString(R.string.loading));
        fragmentCoinrankBinding.homeRy.setOnPullLoadMoreListener(this);
        fragmentCoinrankBinding.homeRy.post(this);
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        mCoinRankInfo = new ArrayList<>();
        mCoinRankAdapter = new CoinRankAdapter(mCoinRankInfo, fragmentCoinrankBinding.homeRy.getContext());
        mCoinRankAdapter.setChildItemClickListener(this);
    }

    /**
     * 监听CoinRank数据
     */
    private void setCoinRankInfoDataListener() {
        mUserProfileViewModel.getUpCoinRankDatas().observe(this, new Observer<CoinRankInfo>() {
            @Override
            public void onChanged(CoinRankInfo coinRankInfo) {
                fragmentCoinrankBinding.loading.setVisibility(View.GONE);
                fragmentCoinrankBinding.homeRy.setVisibility(View.VISIBLE);
                fragmentCoinrankBinding.homeRy.setPullLoadMoreCompleted();
                if (coinRankInfo == null) {
                    noDataUiSetting();
                    return;
                }
                Log.d(TAG, "coinRankInfo==>" + coinRankInfo);
                if (coinRankInfo != null && coinRankInfo.getData() != null && coinRankInfo.getData().getDatas() != null) {
                    fragmentCoinrankBinding.homeRy.setVisibility(View.VISIBLE);
                    mCoinRankInfo.addAll(coinRankInfo.getData().getDatas());
                    mCoinRankAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 没有数据时候的ui设置
     */
    private void noDataUiSetting() {
        if (mCoinRankInfo.size() == 0) {
            setNoDataViewVisible(true);
            fragmentCoinrankBinding.homeRy.setVisibility(View.GONE);
        }
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
    }

    @Override
    public void onLoadMore() {
        num++;
        mUserProfileViewModel.requestCoinInfo(num);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        num = 1;
        mCoinRankInfo.clear();
        fragmentCoinrankBinding.homeRy.setOnPullLoadMoreListener(null);
        fragmentCoinrankBinding.homeRy.removeCallbacks(this);
    }

    @Override
    public void onChildItemClick(View root, int position) {
    }


    @Override
    public void onNoDataClickCallBackByBaseHome() {
        setNoDataViewVisible(false);
        fragmentCoinrankBinding.loading.setVisibility(View.VISIBLE);
        fragmentCoinrankBinding.homeRy.setVisibility(View.GONE);
        mUserProfileViewModel.requestCoinInfo(num);
    }

    @Override
    public void run() {
        fragmentCoinrankBinding.loading.setVisibility(View.VISIBLE);
        fragmentCoinrankBinding.homeRy.setVisibility(View.GONE);
        mUserProfileViewModel.requestCoinInfo(num);
    }

}
