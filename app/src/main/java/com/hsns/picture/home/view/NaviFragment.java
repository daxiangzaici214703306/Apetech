package com.hsns.picture.home.view;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hsns.base.bean.HierarchyInfo;
import com.hsns.base.bean.NaviInfo;
import com.hsns.base.utils.ToastUtils;
import com.hsns.base.view.BaseFragment;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentHomeBinding;
import com.hsns.picture.home.adapter.HierarchyAdapter;
import com.hsns.picture.home.adapter.HomeAdapter;
import com.hsns.picture.home.adapter.NaviAdapter;
import com.hsns.picture.home.viewmodel.HomeViewModel;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NaviFragment extends BaseHomeFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, HomeAdapter.onItemClickListener {
    private NaviAdapter mNaviAdapter;
    private List<NaviInfo.Data> mNaviDatas;
    private static final String TAG = "NaviFragment";


    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater, container);
        setNaviDataListener();
        mNaviDatas = new ArrayList<>();
        mNaviAdapter = new NaviAdapter(mNaviDatas, homeBinding.homeRy.getContext());
        homeBinding.homeRy.setLinearLayout();
//        mNaviAdapter.setChildItemClickListener(this);
        homeBinding.homeRy.setAdapter(mNaviAdapter);
        homeBinding.homeRy.setPushRefreshEnable(false);
        homeBinding.homeRy.setPullRefreshEnable(false);
        homeBinding.homeRy.setOnPullLoadMoreListener(this);
        homeBinding.homeRy.post(new Runnable() {
            @Override
            public void run() {
                homeBinding.loading.setVisibility(View.VISIBLE);
                mHomeViewModel.requestNaviData();
            }
        });
    }

    /**
     * 监听home导航数据
     */
    private void setNaviDataListener() {
        mHomeViewModel.getNaviInfos().observe(this, new Observer<NaviInfo>() {
            @Override
            public void onChanged(NaviInfo naviInfo) {
                homeBinding.loading.setVisibility(View.GONE);
                if (naviInfo == null) {
                    setNoDataViewVisible(true);
                    homeBinding.homeRy.setVisibility(View.GONE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    return;
                }
                Log.d(TAG, "naviInfo==>" + naviInfo.toString());
                if (naviInfo != null && naviInfo.getData() != null) {
                    homeBinding.homeRy.setVisibility(View.VISIBLE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    mNaviDatas.addAll(naviInfo.getData());
                    mNaviAdapter.notifyDataSetChanged();
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

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mNaviDatas.clear();
        homeBinding.homeRy.setOnPullLoadMoreListener(null);
    }

    @Override
    public void onChildItemClick(View root, int position) {
        if (mNaviDatas != null && mNaviDatas.size() > position) {
//            mHierarchyDatas.get(position).getLink();
        }
    }

    @Override
    public void onNoDataClickCallBackByBaseHome() {
        setNoDataViewVisible(false);
        homeBinding.loading.setVisibility(View.VISIBLE);
        mHomeViewModel.requestNaviData();
    }
}
