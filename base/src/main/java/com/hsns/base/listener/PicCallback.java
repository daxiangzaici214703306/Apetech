package com.hsns.base.listener;

import java.util.List;

public interface PicCallback {
    /**
     * 搜索到的图片对象的回调
     * @param photoList
     */
    void onSearchPhotoCallBack(List<String> photoList);
}
