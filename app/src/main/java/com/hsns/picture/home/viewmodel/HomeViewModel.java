package com.hsns.picture.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.bean.HierarchyInfo;
import com.hsns.base.bean.HomeInfo;
import com.hsns.base.bean.NaviInfo;
import com.hsns.base.bean.ProjectInfo;
import com.hsns.base.listener.HomeDataCallback;
import com.hsns.picture.home.model.HomeModel;

public class HomeViewModel extends ViewModel implements IHomeViewModel, HomeDataCallback {
    private HomeModel mHomeModel;
    //首页数据
    private MutableLiveData<HomeInfo> homeInfos = new MutableLiveData<>();
    //体系数据
    private MutableLiveData<HierarchyInfo> hierarchyInfos = new MutableLiveData<>();
    //导航数据
    private MutableLiveData<NaviInfo> naviInfos = new MutableLiveData<>();
    //项目数据
    private MutableLiveData<ProjectInfo> projectInfos = new MutableLiveData<>();

    public MutableLiveData<NaviInfo> getNaviInfos() {
        return naviInfos;
    }

    public MutableLiveData<HierarchyInfo> getHierarchyInfos() {
        return hierarchyInfos;
    }

    public MutableLiveData<HomeInfo> getHomeInfos() {
        return homeInfos;
    }

    public MutableLiveData<ProjectInfo> getProjectInfos() {
        return projectInfos;
    }

    public HomeViewModel() {
        mHomeModel = new HomeModel(this);
    }

    @Override
    public void requestWhichPageData(int num) {
        mHomeModel.requestWhichPageData(num);
    }

    @Override
    public void requsetHierarchyData() {
        mHomeModel.requsetHierarchyData();
    }

    @Override
    public void requestNaviData() {
        mHomeModel.requestNaviData();
    }

    @Override
    public void requestProjectData(int page) {
        mHomeModel.requestProjectData(page);
    }

    @Override
    public void onHomeDataCallback(HomeInfo mHomeInfo) {
        homeInfos.postValue(mHomeInfo);
    }

    @Override
    public void onHierarchyDataCallback(HierarchyInfo mHierarchyInfo) {
        hierarchyInfos.postValue(mHierarchyInfo);
    }

    @Override
    public void onNaviDataCallback(NaviInfo mNaviInfo) {
        naviInfos.postValue(mNaviInfo);
    }

    @Override
    public void onProjectDataCallback(ProjectInfo mProjectInfo) {
        projectInfos.postValue(mProjectInfo);
    }
}
