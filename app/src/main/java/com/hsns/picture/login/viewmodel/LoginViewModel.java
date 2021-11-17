package com.hsns.picture.login.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.bean.LoginResultInfo;
import com.hsns.base.bean.UserInfo;
import com.hsns.base.listener.LoginCallback;
import com.hsns.picture.login.model.LoginModel;

public class LoginViewModel extends ViewModel implements ILoginViewModel, LoginCallback {
    private LoginModel loginModel;
    private MutableLiveData<LoginResultInfo> loginResultInfoMutableLiveData = new MutableLiveData<>();

    public LoginViewModel() {
        loginModel = new LoginModel(this);
    }

    public MutableLiveData<LoginResultInfo> getLoginResultInfoMutableLiveData() {
        return loginResultInfoMutableLiveData;
    }

    @Override
    public void login( Context mContext,UserInfo info) {
        loginModel.login(mContext,info);
    }

    @Override
    public void onLoginCallback(LoginResultInfo loginResultInfo) {
        loginResultInfoMutableLiveData.postValue(loginResultInfo);
    }
}
