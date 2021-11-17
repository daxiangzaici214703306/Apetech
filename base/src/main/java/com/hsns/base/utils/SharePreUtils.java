package com.hsns.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.hsns.base.bean.UserInfo;

public class SharePreUtils {
    /**
     * 更新SharePreference数据
     */
    public static boolean isUserExit(Context mContext, UserInfo info) {
        if (mContext != null) {
            SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
            String resPassword = preferences.getString(info.getUsername(), "");
            if (!TextUtils.isEmpty(resPassword) && resPassword.equals(info.getPassword())) {
                return true;
            }
        } else {
            throw new NullPointerException("context is null !");
        }
        return false;
    }

    /**
     * 插入用户信息
     *
     * @param mContext 上下文
     * @param info     用户信息
     */
    public static void insertUserInfo(Context mContext, UserInfo info) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(info.getUsername(), info.getPassword());
        editor.commit();
    }


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
        return  preferences.getString(BaseUtils.CURUSER,"");
    }


    /**
     * 删除用户信息
     *
     * @param mContext 上下文
     * @param info     用户信息
     */
    public static void deleteUserInfo(Context mContext, UserInfo info) {
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(info.getUsername());
        editor.commit();
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
    public static void storeCookie(Context mContext,String cookie){
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BaseUtils.COOKIE, cookie);
        editor.commit();
    }


    /**
     * 获取网络请求缓存
     */
    public static String getCookie(Context mContext){
        SharedPreferences preferences = mContext.getSharedPreferences(BaseUtils.SHARE_PRE_NAME_USERINFO, Context.MODE_PRIVATE);
        return preferences.getString(BaseUtils.COOKIE, "");
    }
}


