package com.hsns.base.utils;

import android.content.Context;
import android.content.Intent;

public class UiUtils {
    /**
     * 跳转对应Activity的辅助方法
     */
    public static void transActivity(Context mContext, Class c) {
        Intent intent = new Intent();
        intent.setClass(mContext,c);
        mContext.startActivity(intent);
    }

    /**
     * 跳转对应Activity的辅助方法
     */
    public static void transActivity(Context mContext,String tag,Class c) {
        Intent intent = new Intent();
        intent.setClass(mContext,c);
        intent.putExtra(BaseUtils.TAG_FRAGMENT,tag);
        mContext.startActivity(intent);
    }


    /**
     * 跳转到对应fragement
     * @param mContext 上下文
     * @param tag   切换fragment对应的tag
     * @param c     切换fragment所在的父activity
     */
    public static void transFragment(Context mContext, String tag,Class c) {
        Intent intent = new Intent();
        intent.setClass(mContext,c);
//        intent.setAction(BaseUtils.ACTION_MAIN);
        intent.putExtra(BaseUtils.TAG_FRAGMENT,tag);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }

}
