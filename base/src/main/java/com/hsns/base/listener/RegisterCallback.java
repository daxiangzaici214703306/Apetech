package com.hsns.base.listener;

import com.hsns.base.bean.RegisterResultInfo;

public interface RegisterCallback {
    /**
     * 注册信息结果回调
     *
     * @param registerResultInfo 注册结果信息
     */
    void onRegisterCallback(RegisterResultInfo registerResultInfo);
}
