package com.hsns.picture.userprofile.viewmodel;

public interface IUserProfileViewModel {
    /**
     * 请求得到个人信息
     */
    void requestUserProfileInfo();

    /**
     * 请求积分排名
     */
    void requestCoinInfo(int num);

    /**
     * 请求个人积分排名
     */
    void requestPersonCoinInfo(int num);
}
