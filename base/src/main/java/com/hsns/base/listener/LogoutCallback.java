package com.hsns.base.listener;

import com.hsns.base.bean.BaseBean;

public interface LogoutCallback {
    /**
     * 退出登陆请求信息回调
     * @param mLogoutBackInfo 回调数据
     */
    void onLogoutCallback(BaseBean<Object> mLogoutBackInfo);
}
