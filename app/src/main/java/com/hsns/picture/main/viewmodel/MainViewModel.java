package com.hsns.picture.main.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.bean.BaseBean;
import com.hsns.base.listener.LogoutCallback;
import com.hsns.picture.main.model.MainModel;

public class MainViewModel extends ViewModel implements IMainViewModel, LogoutCallback {
    private MainModel mMainModel;
    //退出登陆数据
    private MutableLiveData<BaseBean<Object>> mainDatas = new MutableLiveData<>();

    public MainViewModel() {
        mMainModel = new MainModel(this);
    }

    public MutableLiveData<BaseBean<Object>> getMainDatas() {
        return mainDatas;
    }

    @Override
    public void logout() {
        mMainModel.logout();
    }

    @Override
    public void onLogoutCallback(BaseBean<Object> mLogoutBackInfo) {
        mainDatas.postValue(mLogoutBackInfo);
    }
}
