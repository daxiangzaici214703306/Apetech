package com.hsns.picture.userprofile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.bean.UserProfileInfo;
import com.hsns.base.listener.UserProfileCallback;
import com.hsns.picture.userprofile.model.UserProfileModel;

public class UserProfileViewModel extends ViewModel implements IUserProfileViewModel, UserProfileCallback {
    private UserProfileModel mUserProfileModel;
    private MutableLiveData<UserProfileInfo> upDatas = new MutableLiveData<>();

    public MutableLiveData<UserProfileInfo> getUpDatas() {
        return upDatas;
    }

    public UserProfileViewModel() {
        mUserProfileModel = new UserProfileModel(this);
    }

    @Override
    public void requestUserProfileInfo() {
        mUserProfileModel.requestUserProfileInfo();
    }

    @Override
    public void onUserProfileInfoCallback(UserProfileInfo mUserProfileInfo) {
        upDatas.postValue(mUserProfileInfo);
    }
}
