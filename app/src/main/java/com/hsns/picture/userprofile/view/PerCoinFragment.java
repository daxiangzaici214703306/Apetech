package com.hsns.picture.userprofile.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.hsns.base.bean.PersonCoinInfo;
import com.hsns.base.utils.BaseUtils;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.userprofile.adapter.PerCoinAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PerCoinFragment extends BaseCoinFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener,
        PerCoinAdapter.onItemClickListener, Runnable {
    private PerCoinAdapter mPerCoinAdapter;
    private List<PersonCoinInfo.Datas> mPersonCoinInfo;
    private int num = 1;//当前个人积分排名加载的页数
    private static final String TAG = "CoinRankFragment";


    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater, container);
        setPersonCoinInfoDataListener();
        initAdapter();
        rySetting();

    }

    /**
     * RecyclerView的设置
     */
    private void rySetting() {
        fragmentCoinrankBinding.homeRy.setLinearLayout();
        fragmentCoinrankBinding.homeRy.setAdapter(mPerCoinAdapter);
        fragmentCoinrankBinding.homeRy.setFooterViewText(PictureApplication.getApplication().getString(R.string.loading));
        fragmentCoinrankBinding.homeRy.setOnPullLoadMoreListener(this);
        fragmentCoinrankBinding.homeRy.post(this);
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        mPersonCoinInfo = new ArrayList<>();
        mPerCoinAdapter = new PerCoinAdapter(mPersonCoinInfo, fragmentCoinrankBinding.homeRy.getContext());
        mPerCoinAdapter.setChildItemClickListener(this);
    }

    /**
     * 监听CoinRank数据
     */
    private void setPersonCoinInfoDataListener() {
        mUserProfileViewModel.getUpPersonCoinRankDatas().observe(this, new Observer<PersonCoinInfo>() {
            @Override
            public void onChanged(PersonCoinInfo personCoinInfo) {
                fragmentCoinrankBinding.loading.setVisibility(View.GONE);
                fragmentCoinrankBinding.homeRy.setVisibility(View.VISIBLE);
                fragmentCoinrankBinding.homeRy.setPullLoadMoreCompleted();
                if (personCoinInfo == null) {
                    noDataUiSetting();
                    return;
                }
                Log.d(TAG, "personCoinInfo==>" + personCoinInfo);
                if (personCoinInfo != null && personCoinInfo.getData() != null && personCoinInfo.getData().getDatas() != null) {
                    fragmentCoinrankBinding.homeRy.setVisibility(View.VISIBLE);
                    mPersonCoinInfo.addAll(personCoinInfo.getData().getDatas());
                    mPerCoinAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 没有数据时候的ui设置
     */
    private void noDataUiSetting() {
        if (mPersonCoinInfo.size() == 0) {
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
        mUserProfileViewModel.requestPersonCoinInfo(num);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        num = 1;
        mPersonCoinInfo.clear();
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
        mUserProfileViewModel.requestPersonCoinInfo(num);
    }


    @Override
    public void run() {
        fragmentCoinrankBinding.loading.setVisibility(View.VISIBLE);
        fragmentCoinrankBinding.homeRy.setVisibility(View.GONE);
        mUserProfileViewModel.requestPersonCoinInfo(num);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }
}
