package com.hsns.picture.home.model;

import com.hsns.base.listener.HomeDataCallback;
import com.hsns.network.manager.HttpManager;
import com.hsns.picture.PictureApplication;

public class HomeModel implements IHomeModel {
    private HomeDataCallback mHomeDataCallback;
    public HomeModel(HomeDataCallback mHomeDataCallback) {
       this.mHomeDataCallback=mHomeDataCallback;
    }

    @Override
    public void requestWhichPageData(int num) {
        HttpManager.getInstance(PictureApplication.getApplication()).requestHomeInfo(num,mHomeDataCallback);
    }

    @Override
    public void requsetHierarchyData() {
        HttpManager.getInstance(PictureApplication.getApplication()).requestHieerarchyInfo(mHomeDataCallback);
    }

    @Override
    public void requestNaviData() {
        HttpManager.getInstance(PictureApplication.getApplication()).requestNaviInfo(mHomeDataCallback);
    }

    @Override
    public void requestProjectData(int page) {
        HttpManager.getInstance(PictureApplication.getApplication()).requestProjectInfo(page,mHomeDataCallback);
    }

    @Override
    public void requestBannerData() {
        HttpManager.getInstance(PictureApplication.getApplication()).requestBannerData(mHomeDataCallback);
    }
}
