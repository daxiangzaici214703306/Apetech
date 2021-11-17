package com.hsns.picture.photos.model;

import com.hsns.base.listener.PicCallback;
import com.hsns.base.manager.FileManager;

public class PhotoModel implements IPhotoModel {

    public PhotoModel(PicCallback mPicCallback) {
        FileManager.getInstance().setPhotoCallBack(mPicCallback);
    }

    @Override
    public void findPhotosBySd() {
        FileManager.getInstance().getPhotoList();
    }
}
