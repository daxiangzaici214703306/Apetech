package com.hsns.picture.register.viewmodel;

import android.content.Context;

import com.hsns.base.bean.RegisterInfo;
import com.hsns.base.bean.UserInfo;

public interface IRegisterViewModel {
    /**
     * 注册
     */
    void register(Context mContext, RegisterInfo info);

}
