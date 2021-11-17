package com.hsns.picture.home.view;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.hsns.base.view.BaseFragment;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentHomeBinding;
import com.hsns.picture.home.viewmodel.HomeViewModel;


public abstract class BaseHomeFragment extends BaseFragment implements NoDataView.NoDataClickCallBack {
    public FragmentHomeBinding homeBinding;
    public NoDataView mNoDataView;
    public HomeViewModel mHomeViewModel;
    private Handler mHandler = new Handler();
    private static final String TAG = "BaseHomeFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        homeBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mHomeViewModel = (HomeViewModel) getViewModel(HomeViewModel.class);
        initNoDataView();
    }

    @Override
    public View getResultView() {
        return homeBinding.getRoot();
    }

    private void initNoDataView() {
        mNoDataView = new NoDataView(homeBinding.noData, this);
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
            homeBinding.homeRy.setPullLoadMoreCompleted();
        }
    };


}
