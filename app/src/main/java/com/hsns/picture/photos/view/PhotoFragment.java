package com.hsns.picture.photos.view;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hsns.base.view.BaseFragment;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentPhotoBinding;
import com.hsns.picture.photos.adapter.PhotoAdapter;
import com.hsns.picture.photos.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends BaseFragment {
    private FragmentPhotoBinding photoBinding;
    private PhotoViewModel photoViewModel;
    private List<String> photos;
    private PhotoAdapter mPhotoAdapter;
    private static final String TAG = "PhotoFragment";
    private static final int RY_COLUME = 11;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        photoBinding = DataBindingUtil.inflate(inflater, getLayoutId(),container,false);
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        photoViewModel = new ViewModelProvider(this, factory).get(PhotoViewModel.class);
        dataListener();
        photos = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(photos,photoBinding.photoRy.getContext());
        GridLayoutManager mGridLayoutManager=new GridLayoutManager(photoBinding.photoRy.getContext(), RY_COLUME);
        photoBinding.photoRy.setLayoutManager(mGridLayoutManager);
        photoBinding.photoRy.setAdapter(mPhotoAdapter);
        photoViewModel.findPhotosBySd();
    }

    @Override
    public View getResultView() {
        return photoBinding.getRoot();
    }

    /**
     * 数据监听
     */
    private void dataListener() {
        photoViewModel.getPhotosData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> list) {
                Log.i(TAG, "photos data ==>" + list+" mPhotoAdapter==>"+mPhotoAdapter);
                if (mPhotoAdapter != null) {
                    photos.clear();
                    photos.addAll(list);
                    mPhotoAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
