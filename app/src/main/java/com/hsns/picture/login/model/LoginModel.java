package com.hsns.picture.login.model;

import android.content.Context;
import com.hsns.base.bean.UserInfo;
import com.hsns.base.listener.LoginCallback;
import com.hsns.base.utils.SharePreUtils;
import com.hsns.network.manager.HttpManager;
import com.hsns.picture.PictureApplication;

public class LoginModel implements ILoginModel {
    private LoginCallback loginCallback;

    public LoginModel(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

    @Override
    public void login(Context mContext, UserInfo info) {
        //网络请求方式,这里因为没有服务器所以暂时不使用这种方式
        HttpManager.getInstance(PictureApplication.getApplication()).requestUserInfo(info, loginCallback);
        //本地获取方式
//        boolean isUserExit = SharePreUtils.isUserExit(mContext, info);
        SharePreUtils.insertCurLoginInfo(mContext,info);//存储当前登录的用户
//        loginCallback.onLoginCallback(isUserExit ? info.getUsername() : "");
    }

}
