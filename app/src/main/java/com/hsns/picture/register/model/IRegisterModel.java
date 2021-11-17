package com.hsns.picture.register.model;

import android.content.Context;

import com.hsns.base.bean.RegisterInfo;
import com.hsns.base.bean.UserInfo;

public interface IRegisterModel {
    /**
     * 注册
     */
    void register(Context mContext, RegisterInfo info);
}
