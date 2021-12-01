package com.hsns.picture.userprofile.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.base.view.BaseFragment;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentCoinrankBinding;
import com.hsns.picture.databinding.FragmentHomeBinding;
import com.hsns.picture.home.view.NoDataView;
import com.hsns.picture.home.viewmodel.HomeViewModel;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.userprofile.model.UserProfileModel;
import com.hsns.picture.userprofile.viewmodel.UserProfileViewModel;


public abstract class BaseCoinFragment extends BaseFragment implements NoDataView.NoDataClickCallBack, View.OnClickListener {
    public FragmentCoinrankBinding fragmentCoinrankBinding;
    public NoDataView mNoDataView;
    public UserProfileViewModel mUserProfileViewModel;
    private Handler mHandler = new Handler();
    private static final String TAG = "BaseCoinFragment";
    private String tittle="";//标题

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coinrank;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        fragmentCoinrankBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mUserProfileViewModel = (UserProfileViewModel) getViewModel(UserProfileViewModel.class);
        initNoDataView();
        initBackView();
    }

    private void initBackView() {
        fragmentCoinrankBinding.back.setOnClickListener(this);
    }

    @Override
    public View getResultView() {
        return fragmentCoinrankBinding.getRoot();
    }

    private void initNoDataView() {
        mNoDataView = new NoDataView(fragmentCoinrankBinding.noData, this);
    }


    @Override
    public void onNoDataClickCallBack() {
        onNoDataClickCallBackByBaseHome();
    }


    /**
     * 设置没有数据时候view的显示与不显示
     *
     * @param visible 显示与否
     */
    public void setNoDataViewVisible(boolean visible) {
        mNoDataView.setVisible(visible);
    }


    /**
     * 获取对应的viewmodel
     *
     * @param T
     * @return
     */
    public ViewModel getViewModel(Class T) {
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        ViewModel mViewModel = new ViewModelProvider(this, factory).get(T);
        return mViewModel;
    }


    /**
     * 没有数据回调给对应的fragment
     */
    public abstract void onNoDataClickCallBackByBaseHome();

    /**
     * 下拉刷新操作
     */
    public void onRefresh() {
        mHandler.postDelayed(refreshRunnable, 1500);
    }


    private Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            fragmentCoinrankBinding.homeRy.setPullLoadMoreCompleted();
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:
                getActivity().finish();
                break;
        }
    }

    /**
     * 设置标题内容
     *
     * @param content 内容
     */
    public void setTittleContent(String content) {
        this.tittle = content;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCoinrankBinding.tittle.setText(tittle);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (!hidden) fragmentCoinrankBinding.tittle.setText(tittle);
    }
}
