package com.hsns.picture.register.model;

import android.content.Context;

import com.hsns.base.bean.RegisterInfo;
import com.hsns.base.bean.UserInfo;
import com.hsns.base.listener.LoginCallback;
import com.hsns.base.listener.RegisterCallback;
import com.hsns.base.utils.SharePreUtils;
import com.hsns.network.manager.HttpManager;
import com.hsns.picture.PictureApplication;

public class RegisterModel implements IRegisterModel {
    private RegisterCallback registerCallback;

    public RegisterModel(RegisterCallback registerCallback) {
        this.registerCallback = registerCallback;
    }


    @Override
    public void register(Context mContext, RegisterInfo info) {
//        SharePreUtils.insertUserInfo(mContext, info);
        HttpManager.getInstance(PictureApplication.getApplication()).registerUser(info,registerCallback);
    }
}
