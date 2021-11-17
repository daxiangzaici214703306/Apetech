package com.hsns.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements OnClickListener {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView(inflater, container);
        return getResultView();
    }


    /**
     * 获取对应layout的id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化view
     *
     * @return
     */
    public abstract void initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 获取对应的布局
     */
    public abstract View getResultView();

    @Override
    public void onClick(View v) {

    }
}
