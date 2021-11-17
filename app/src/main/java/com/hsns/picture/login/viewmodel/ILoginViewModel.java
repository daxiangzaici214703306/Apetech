package com.hsns.picture.login.viewmodel;

import android.content.Context;

import com.hsns.base.bean.UserInfo;

public interface ILoginViewModel {
    /**
     * 登陆
     */
    void login(Context mContext,UserInfo info);

}
