package com.hsns.network.constant;

public class RetrofitConstants {
    //服务器地址
    public final static String BASE_URL = "https://www.wanandroid.com/";

    //服务器返回信息的错误码
    public final static int ERROR_CODE_SUCCESS = 0;//表示获取数据成功
    public static final int ERROR_CODE_TOKEN_INVALID = 401;;//表示Token 过期
    public static final int ERROR_CODE_NOT_LOGIN = -1001;;//表示没有登录

    public static final String SAVE_USER_LOGIN_KEY = "user/login";
    public static final String SAVE_USER_REGISTER_KEY = "user/register";
    public static final String COLLECTIONS_WEBSITE = "lg/collect";
    public static final String UNCOLLECTIONS_WEBSITE = "lg/uncollect";
    public static final String ARTICLE_WEBSITE = "article";
    public static final String TODO_WEBSITE = "lg/todo";
    public static final String COIN_WEBSITE = "lg/coin";
    public static final String USERINFO_WEBSITE = "lg/userinfo";
    public static final String SET_COOKIE_KEY = "set-cookie";
    public static final String COOKIE_NAME = "Cookie";
}
