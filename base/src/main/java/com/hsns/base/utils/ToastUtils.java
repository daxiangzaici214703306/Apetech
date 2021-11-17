package com.hsns.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtils {
    /**
     * 显示Toast的方法
     * @param mContext 上下文
     * @param id  资源id
     */
    public static void showToast(Context mContext, int id) {
        if (mContext != null) {
            Toast.makeText(mContext, mContext.getString(id), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示Toast的方法
     *
     * @param mContext 上下文
     * @param content 显示内容
     */
    public static void showToast(Context mContext, String content) {
        if (mContext != null) {
            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }
    }
}
