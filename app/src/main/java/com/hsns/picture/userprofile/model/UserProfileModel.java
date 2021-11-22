package com.hsns.picture.userprofile.model;

import androidx.lifecycle.ViewModel;

import com.hsns.base.listener.UserProfileCallback;
import com.hsns.network.manager.HttpManager;
import com.hsns.picture.PictureApplication;

public class UserProfileModel implements IUserProfileModel {
    private UserProfileCallback mUserProfileCallback;


    public UserProfileModel(UserProfileCallback mUserProfileCallback) {
        this.mUserProfileCallback = mUserProfileCallback;
    }

    @Override
    public void requestUserProfileInfo() {
        HttpManager.getInstance(PictureApplication.getApplication()).requestUserProfileInfo(mUserProfileCallback);
    }

    @Override
    public void requestCoinInfo(int num) {
        HttpManager.getInstance(PictureApplication.getApplication()).requestCoinInfo(num,mUserProfileCallback);
    }

    @Override
    public void requestPersonCoinInfo(int num) {
        HttpManager.getInstance(PictureApplication.getApplication()).requestPersonCoinInfo(num,mUserProfileCallback);
    }
}
