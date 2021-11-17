package com.hsns.picture.login.model;

import android.content.Context;

import com.hsns.base.bean.UserInfo;

public interface ILoginModel {
    /**
     * 登陆
     */
    void login(Context mContext,UserInfo info);

}
