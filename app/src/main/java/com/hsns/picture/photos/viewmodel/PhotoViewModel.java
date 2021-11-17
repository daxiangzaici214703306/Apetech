package com.hsns.picture.photos.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsns.base.listener.PicCallback;
import com.hsns.picture.photos.model.PhotoModel;

import java.util.List;

public class PhotoViewModel extends ViewModel implements IPhotoViewModel, PicCallback {
    private PhotoModel mPhotoModel;

    public MutableLiveData<List<String>> getPhotosData() {
        return photosData;
    }

    private MutableLiveData<List<String>> photosData=new MutableLiveData<>();
    public PhotoViewModel() {
        mPhotoModel=new PhotoModel(this);
    }

    @Override
    public void onSearchPhotoCallBack(List<String> photoList) {
       photosData.postValue(photoList);
    }

    @Override
    public void findPhotosBySd() {
        mPhotoModel.findPhotosBySd();
    }
}
