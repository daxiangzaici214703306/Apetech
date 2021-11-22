package com.hsns.picture.userprofile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.bean.CoinRankInfo;
import com.hsns.base.bean.PersonCoinInfo;
import com.hsns.base.bean.UserProfileInfo;
import com.hsns.base.listener.UserProfileCallback;
import com.hsns.picture.userprofile.model.UserProfileModel;

public class UserProfileViewModel extends ViewModel implements IUserProfileViewModel, UserProfileCallback {
    private UserProfileModel mUserProfileModel;
    //个人中心信息数据
    private MutableLiveData<UserProfileInfo> upDatas = new MutableLiveData<>();
    //积分排名信息数据
    private MutableLiveData<CoinRankInfo> upCoinRankDatas = new MutableLiveData<>();
    //个人积分排名信息数据
    private MutableLiveData<PersonCoinInfo> upPersonCoinRankDatas = new MutableLiveData<>();

    public MutableLiveData<UserProfileInfo> getUpDatas() {
        return upDatas;
    }

    public MutableLiveData<CoinRankInfo> getUpCoinRankDatas() {
        return upCoinRankDatas;
    }

    public MutableLiveData<PersonCoinInfo> getUpPersonCoinRankDatas() {
        return upPersonCoinRankDatas;
    }

    public UserProfileViewModel() {
        mUserProfileModel = new UserProfileModel(this);
    }

    @Override
    public void requestUserProfileInfo() {
        mUserProfileModel.requestUserProfileInfo();
    }

    @Override
    public void requestCoinInfo(int num) {
        mUserProfileModel.requestCoinInfo(num);
    }

    @Override
    public void requestPersonCoinInfo(int num) {
        mUserProfileModel.requestPersonCoinInfo(num);
    }

    @Override
    public void onUserProfileInfoCallback(UserProfileInfo mUserProfileInfo) {
        upDatas.postValue(mUserProfileInfo);
    }

    @Override
    public void onCoinRankCallback(CoinRankInfo mCoinRankInfo) {
        upCoinRankDatas.postValue(mCoinRankInfo);
    }

    @Override
    public void onPersonCoinInfoCallback(PersonCoinInfo mPersonCoinInfo) {
        upPersonCoinRankDatas.postValue(mPersonCoinInfo);
    }
}
