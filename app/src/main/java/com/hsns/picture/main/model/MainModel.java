package com.hsns.picture.main.model;

import com.hsns.base.listener.LogoutCallback;
import com.hsns.network.manager.HttpManager;
import com.hsns.picture.PictureApplication;

public class MainModel implements IMainModel {
    private LogoutCallback mLogoutCallback;

    public MainModel(LogoutCallback mLogoutCallback) {
        this.mLogoutCallback = mLogoutCallback;
    }

    @Override
    public void logout() {
        HttpManager.getInstance(PictureApplication.getApplication()).requestLogout(mLogoutCallback);
    }
}
