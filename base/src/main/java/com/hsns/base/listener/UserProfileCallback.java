package com.hsns.base.listener;

import com.hsns.base.bean.UserProfileInfo;

public interface UserProfileCallback {
    /**
     * 个人中心数据回调
     * @param mUserProfileInfo 个人中心数据
     */
    void onUserProfileInfoCallback(UserProfileInfo mUserProfileInfo);
}
