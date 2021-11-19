package com.hsns.picture.home.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.hsns.base.bean.HierarchyInfo;
import com.hsns.picture.home.adapter.HierarchyAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HierarchyFragment extends BaseHomeFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, HierarchyAdapter.onItemClickListener {
    private HierarchyAdapter mHierarchyAdapter;
    private List<HierarchyInfo.Data> mHierarchyDatas;
    private static final String TAG = "HierarchyFragment";


    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater,container);
        setHierarchyDataListener();
        mHierarchyDatas = new ArrayList<>();
        mHierarchyAdapter = new HierarchyAdapter(mHierarchyDatas, homeBinding.homeRy.getContext());
        homeBinding.homeRy.setLinearLayout();
        homeBinding.homeRy.setAdapter(mHierarchyAdapter);
        homeBinding.homeRy.setPushRefreshEnable(false);
        homeBinding.homeRy.setPullRefreshEnable(false);
        homeBinding.homeRy.setOnPullLoadMoreListener(this);
        homeBinding.homeRy.post(new Runnable() {
            @Override
            public void run() {
                homeBinding.loading.setVisibility(View.VISIBLE);
                mHomeViewModel.requsetHierarchyData();
            }
        });
    }


    /**
     * 监听home文章数据
     */
    private void setHierarchyDataListener() {
        mHomeViewModel.getHierarchyInfos().observe(this, new Observer<HierarchyInfo>() {
            @Override
            public void onChanged(HierarchyInfo hierarchyInfo) {
                homeBinding.loading.setVisibility(View.GONE);
                if (hierarchyInfo == null) {
                    setNoDataViewVisible(true);
                    homeBinding.homeRy.setVisibility(View.GONE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    return;
                }
                Log.d(TAG, "hierarchyInfo==>" + hierarchyInfo);
                if (hierarchyInfo != null && hierarchyInfo.getData() != null) {
                    homeBinding.homeRy.setVisibility(View.VISIBLE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    mHierarchyDatas.addAll(hierarchyInfo.getData());
                    mHierarchyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHierarchyDatas.clear();
        homeBinding.homeRy.setOnPullLoadMoreListener(null);
    }

    @Override
    public void onChildItemClick(View root, int position) {
        if (mHierarchyDatas != null && mHierarchyDatas.size() > position) {
//            mHierarchyDatas.get(position).getLink();
        }
    }

    @Override
    public void onNoDataClickCallBackByBaseHome() {
        setNoDataViewVisible(false);
        homeBinding.loading.setVisibility(View.VISIBLE);
        mHomeViewModel.requsetHierarchyData();
    }


}
