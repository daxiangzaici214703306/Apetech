package com.hsns.base.listener;

import com.hsns.base.bean.CoinRankInfo;
import com.hsns.base.bean.PersonCoinInfo;
import com.hsns.base.bean.UserProfileInfo;

public interface UserProfileCallback {
    /**
     * 个人中心数据回调
     * @param mUserProfileInfo 个人中心数据
     */
    void onUserProfileInfoCallback(UserProfileInfo mUserProfileInfo);

    /**
     * 积分排名的信息回调
     * @param mCoinRankInfo 积分排名数据对象
     */
    void onCoinRankCallback(CoinRankInfo mCoinRankInfo);
    /**
     * 个人积分信息回调
     * @param mPersonCoinInfo 个人积分信息数据
     */
    void onPersonCoinInfoCallback(PersonCoinInfo mPersonCoinInfo);
}
