package com.hsns.picture.home.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.hsns.base.bean.ProjectInfo;
import com.hsns.base.manager.PageChangeManger;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.home.adapter.ProjectAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends BaseHomeFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, ProjectAdapter.onItemClickListener {
    private ProjectAdapter mProjectAdapter;
    private List<ProjectInfo.Datas> mProjectDatas;
    private int num = 0;//当前文章加载的页数
    private static final String TAG = "HomeFragment";


    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater,container);
        setProjectDataListener();
        mProjectDatas = new ArrayList<>();
        mProjectAdapter = new ProjectAdapter(mProjectDatas, homeBinding.homeRy.getContext());
        homeBinding.homeRy.setLinearLayout();
        homeBinding.homeRy.setAdapter(mProjectAdapter);
        homeBinding.homeRy.setFooterViewText(PictureApplication.getApplication().getString(R.string.loading));
        mProjectAdapter.setChildItemClickListener(this);
        homeBinding.homeRy.setOnPullLoadMoreListener(this);
        homeBinding.homeRy.post(new Runnable() {
            @Override
            public void run() {
                homeBinding.loading.setVisibility(View.VISIBLE);
                mHomeViewModel.requestProjectData(num);
            }
        });
    }

    /**
     * 监听project数据
     */
    private void setProjectDataListener() {
        mHomeViewModel.getProjectInfos().observe(this, new Observer<ProjectInfo>() {
            @Override
            public void onChanged(ProjectInfo projectInfo) {
                homeBinding.loading.setVisibility(View.GONE);
                if(projectInfo==null) {
                    setNoDataViewVisible(true);
                    homeBinding.homeRy.setVisibility(View.GONE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    return;
                }
                Log.d(TAG, "projectInfo==>" + projectInfo.toString());
                if (projectInfo != null && projectInfo.getData() != null && projectInfo.getData().getDatas() != null) {
                    homeBinding.homeRy.setVisibility(View.VISIBLE);
                    homeBinding.homeRy.setPullLoadMoreCompleted();
                    mProjectDatas.addAll(projectInfo.getData().getDatas());
                    mProjectAdapter.notifyDataSetChanged();
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
        mHomeViewModel.requestProjectData(num);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        num = 0;
        mProjectDatas.clear();
        homeBinding.homeRy.setOnPullLoadMoreListener(null);
    }

    @Override
    public void onChildItemClick(View root, int position) {
        Log.d(TAG,"onChildItemClick position==>"+position);
        if (mProjectDatas != null && mProjectDatas.size() > position) {
            UiUtils.transFragment(getActivity(), BaseUtils.TAG_WEBVIEW, MainActivity.class);
            PageChangeManger.getInstance().getListener().onPageChange(mProjectDatas.get(position).getProjectLink());
        }
    }


    @Override
    public void onNoDataClickCallBackByBaseHome() {
        setNoDataViewVisible(false);
        homeBinding.loading.setVisibility(View.VISIBLE);
        mHomeViewModel.requestProjectData(num);
    }
}
