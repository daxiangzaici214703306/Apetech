package com.hsns.base.utils;

public class BaseUtils {
    public static final String TAG_LOGIN = "login";
    public static final String TAG_PHOTOS = "photos";
    public static final String TAG_REGISTER = "register";
    public static final String TAG_USERPROFILE = "userprofile";
    public static final String TAG_UPDATE = "update";
    public static final String TAG_SETTING = "setting";
    public static final String TAG_HOME = "home";
    public static final String TAG_PROJECT = "project";
    public static final String TAG_NAVI = "navi";
    public static final String TAG_SYSTEM = "system";
    public static final String TAG_WEBVIEW = "webview";
    public static final String TAG_COINRANKINFO = "coinrank";
    public static final String TAG_PERCOININFO = "percoin";
    public static final String TAG_BACK = "back";
    //跳转fragment对应的tag值
    public static final String TAG_FRAGMENT = "tag_f";
    public static final String TAG_WEBVIEW_URL = "url";
    //MainActivity对应的action
    public static final String ACTION_MAIN = "android.intent.action.MAIN";


    //Sharepreference变量
    public static final String SHARE_PRE_NAME_USERINFO="logininfo";
    public static final String SHARE_PRE_VALUE_USER="user";
    public static final String SHARE_PRE_VALUE_PASSWORD="password";
    public static boolean isLoginSuccess=false;//默认没有登录的
    public static final String ISLOGIN="isLogin";//SharePre保存是否登录的状态
    public static final String CURUSER="curUser";//SharePre当前登录的用户名
    public static final String CURPASSWORD="curPassword";//SharePre当前登录的用户密码
    public static final String COOKIE="cookie";//网络请求的cookies

}
