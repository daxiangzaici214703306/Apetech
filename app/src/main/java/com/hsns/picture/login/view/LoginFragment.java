package com.hsns.picture.login.view;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hsns.base.bean.LoginResultInfo;
import com.hsns.base.bean.UserInfo;
import com.hsns.base.utils.SharePreUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.ToastUtils;
import com.hsns.base.view.BaseFragment;
import com.hsns.network.constant.RetrofitConstants;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentLoginBinding;
import com.hsns.picture.login.viewmodel.LoginViewModel;

public class LoginFragment extends BaseFragment {
    private View view;
    private FragmentLoginBinding loginBinding;
    private LoginViewModel mLoginViewModel;
    private static final String TAG = "LoginFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        view = LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        loginBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        mLoginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        setLiveDataListener();
        initData();
        loginBinding.loginBtn.setOnClickListener(this);
        loginBinding.loginRegisBtn.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 设置登陆数据获取的监听
     */
    private void setLiveDataListener() {
        mLoginViewModel.getLoginResultInfoMutableLiveData().observe(this, new Observer<LoginResultInfo>() {
            @Override
            public void onChanged(LoginResultInfo loginResultInfo) {
                if(loginResultInfo==null){
                    ToastUtils.showToast(getActivity(), R.string.login_fail);
                    return;
                }
                Log.d(TAG,"loginResultInfo==>"+loginResultInfo.toString());
                if (loginResultInfo.getData()!=null&&loginResultInfo!=null&&loginResultInfo.getErrorCode()== RetrofitConstants.ERROR_CODE_SUCCESS) {
                    ToastUtils.showToast(getActivity(), R.string.login_success);
                    BaseUtils.isLoginSuccess = true;
                    SharePreUtils.updateLoginStatus(getActivity(), true);
                    UiUtils.transFragment(getActivity(), BaseUtils.TAG_BACK, MainActivity.class);
                } else {
                    ToastUtils.showToast(getActivity(), R.string.login_fail);
                    BaseUtils.isLoginSuccess = false;
                    SharePreUtils.updateLoginStatus(getActivity(), false);
                }
            }
        });
    }

    @Override
    public View getResultView() {
        return loginBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_btn:
                doLoginOperation();
                break;
            case R.id.login_regis_btn:
                UiUtils.transFragment(PictureApplication.getApplication(), BaseUtils.TAG_REGISTER, MainActivity.class);
                break;
        }
    }

    /**
     * 登陆点击事件的操作
     */
    private void doLoginOperation() {
        String userName = loginBinding.loginUserEdt.getText().toString().trim();
        String password = loginBinding.loginPasswordEdt.getText().toString().trim();
        UserInfo info = new UserInfo(userName, password);
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            mLoginViewModel.login(getActivity(), info);
        } else {
            ToastUtils.showToast(getActivity(), R.string.user_or_pass_is_null);
        }
    }


}
