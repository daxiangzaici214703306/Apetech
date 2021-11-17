package com.hsns.base.listener;

import com.hsns.base.bean.LoginResultInfo;

public interface LoginCallback {

    /**
     * 登陆的回调
     *
     * @param loginResultInfo 登陆返回的数据
     */
    void onLoginCallback(LoginResultInfo loginResultInfo);
}
