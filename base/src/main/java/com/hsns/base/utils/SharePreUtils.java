package com.hsns.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.text.TextUtils;

import com.hsns.base.bean.UserInfo;

public class SharePreUtils {
    /**
     * 插入当前登录用户信息
     *
     * @param mContext 上下文
     * @param info     用户信息
     */
    public static void insertCurLoginInfo(Context mContext, UserInfo info) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BaseUtils.CURUSER, info.getUsername());
        editor.putString(BaseUtils.CURPASSWORD, info.getPassword());
        editor.commit();
    }

    /**
     * 得到当前登录用户信息
     *
     * @param mContext 上下文
     */
    public static String getCurLoginInfo(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        return preferences.getString(BaseUtils.CURUSER, "");
    }

    /**
     * 判断是否登录了
     */
    public static boolean isLogin(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        return preferences.getBoolean(BaseUtils.ISLOGIN, false);
    }


    /**
     * 更新登录状态
     */
    public static void updateLoginStatus(Context mContext, boolean isLogin) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BaseUtils.ISLOGIN, isLogin);
        editor.commit();
    }

    /**
     * 判断新用户是否登录
     *
     * @param mContext     上下文
     * @param curLoginUser 当前登录的用户
     * @return
     */
    public static boolean isUserChanged(Context mContext, String curLoginUser) {
        if (curLoginUser == null) return false;
        return curLoginUser.equals(getCurLoginInfo(mContext));
    }

    /**
     * 清除登录状态信息
     */
    public static void clearLoginStatus(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BaseUtils.ISLOGIN, false);
        editor.commit();
    }


    /**
     * 保存网络请求缓存
     */
    public static void storeCookie(Context mContext, String cookie) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BaseUtils.COOKIE, cookie);
        editor.commit();
    }


    /**
     * 获取网络请求缓存
     */
    public static String getCookie(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        return preferences.getString(BaseUtils.COOKIE, "");
    }


    /**
     * 存储头像路径
     *
     * @param mContext
     * @param path
     */
    public static void storePicInfo(Context mContext, String path, boolean isCut) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BaseUtils.IMGDATA, path);
        editor.putBoolean(BaseUtils.IMGCUT, isCut);
        editor.commit();
    }

    /**
     * 存储头像图片路径
     * @param mContext
     * @return
     */
    public static String getPicPath(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        return preferences.getString(BaseUtils.IMGDATA, "");
    }


    /**
     * 存储的图像是否被裁减
     * @param mContext
     * @return
     */
    public static boolean isCut(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        return preferences.getBoolean(BaseUtils.IMGCUT, false);
    }


    /**
     * 清楚用户信息
     * @param mContext
     */
    public static void clearUserInfo(Context mContext){
        SharePreUtils.clearLoginStatus(mContext);
        SharePreUtils.storeCookie(mContext, "");//清楚cookies
//        storePicInfo(mContext,"",false);
    }

}


