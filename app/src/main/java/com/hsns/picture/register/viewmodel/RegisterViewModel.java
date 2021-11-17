package com.hsns.picture.register.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.bean.RegisterInfo;
import com.hsns.base.bean.RegisterResultInfo;
import com.hsns.base.bean.UserInfo;
import com.hsns.base.listener.LoginCallback;
import com.hsns.base.listener.RegisterCallback;
import com.hsns.picture.login.model.LoginModel;
import com.hsns.picture.register.model.RegisterModel;

public class RegisterViewModel extends ViewModel implements IRegisterViewModel, RegisterCallback {
    private RegisterModel registerModel;
    private MutableLiveData<RegisterResultInfo> registerResultInfos = new MutableLiveData<>();

    public RegisterViewModel() {
        registerModel = new RegisterModel(this);
    }

    public MutableLiveData<RegisterResultInfo> getTokens() {
        return registerResultInfos;
    }

    @Override
    public void register(Context mContext, RegisterInfo info) {
        registerModel.register(mContext,info);
    }

    @Override
    public void onRegisterCallback(RegisterResultInfo registerResultInfo) {
        registerResultInfos.postValue(registerResultInfo);
    }
}
