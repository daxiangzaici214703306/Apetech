package com.hsns.picture.home.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.hsns.base.bean.HomeInfo;
import com.hsns.base.manager.PageChangeManger;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.home.adapter.HomeAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseHomeFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, HomeAdapter.onItemClickListener {
    private HomeAdapter mHomeAdapter;
    private List<HomeInfo.Datas> mHomeDatas;
    private int num = 0;//当前文章加载的页数
    private static final String TAG = "HomeFragment";

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater,container);
        setHomeDataListener();
        mHomeDatas = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mHomeDatas, homeBinding.homeRy.getContext());
        homeBinding.homeRy.setLinearLayout();
        homeBinding.homeRy.setAdapter(mHomeAdapter);
        homeBinding.homeRy.setFooterViewText(PictureApplication.getApplication().getString(R.string.loading));
        mHomeAdapter.setChildItemClickListener(this);
        homeBinding.homeRy.setOnPullLoadMoreListener(this);
        homeBinding.homeRy.post(new Runnable() {
            @Override
            public void run() {
                homeBinding.loading.setVisibility(View.VISIBLE);
                mHomeViewModel.requestWhichPageData(num);
            }
        });
    }


    /**
     * 监听home文章数据
     */
    private void setHomeDataListener() {
        mHomeViewModel.getHomeInfos().observe(this, new Observer<HomeInfo>() {
            @Override
            public void onChanged(HomeInfo homeInfo) {
                homeBinding.loading.setVisibility(View.GONE);
                if (homeInfo == null) {
                    mNoDataView.setVisible(true);
                    homeBinding.homeRy.setVisibility(View.GONE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    return;
                }
                Log.d(TAG, "homeInfo==>" + homeInfo.toString());
                if (homeInfo != null && homeInfo.getData() != null && homeInfo.getData().getDatas() != null) {
                    homeBinding.homeRy.setVisibility(View.VISIBLE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    mHomeDatas.addAll(homeInfo.getData().getDatas());
                    mHomeAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
    }

    @Override
    public void onLoadMore() {
        num++;
        mHomeViewModel.requestWhichPageData(num);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        num = 0;
        mHomeDatas.clear();
        homeBinding.homeRy.setOnPullLoadMoreListener(null);
    }

    @Override
    public void onChildItemClick(View root, int position) {
        Log.d(TAG, "onChildItemClick position==>" + position);
        if (mHomeDatas != null && mHomeDatas.size() > position) {
            UiUtils.transFragment(getActivity(), BaseUtils.TAG_WEBVIEW, MainActivity.class);
            PageChangeManger.getInstance().getListener().onPageChange(mHomeDatas.get(position).getLink());
        }
    }

    @Override
    public void onNoDataClickCallBackByBaseHome() {
        mNoDataView.setVisible(false);
        homeBinding.loading.setVisibility(View.VISIBLE);
        mHomeViewModel.requestWhichPageData(num);
    }
}
