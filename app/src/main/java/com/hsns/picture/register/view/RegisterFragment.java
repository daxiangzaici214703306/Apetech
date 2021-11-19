package com.hsns.picture.register.view;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hsns.base.bean.RegisterInfo;
import com.hsns.base.bean.RegisterResultInfo;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.ToastUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.base.view.BaseFragment;
import com.hsns.network.constant.RetrofitConstants;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentRegisterBinding;
import com.hsns.picture.register.viewmodel.RegisterViewModel;

public class RegisterFragment extends BaseFragment {
    private FragmentRegisterBinding registerBinding;
    private RegisterViewModel mRegisterViewModel;
    private static final String TAG = "RegisterFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        registerBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        mRegisterViewModel = new ViewModelProvider(this, factory).get(RegisterViewModel.class);
        setLiveDataListener();
        registerBinding.registerBtn.setOnClickListener(this);
    }

    /**
     * 设置登陆数据获取的监听
     */
    private void setLiveDataListener() {
        mRegisterViewModel.getTokens().observe(this, new Observer<RegisterResultInfo>() {
            @Override
            public void onChanged(final RegisterResultInfo registerResultInfo) {
                if(registerResultInfo==null) return;
                Log.d(TAG, "registerresultinfo==>" + registerResultInfo);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        doRegisterUpdateUI(registerResultInfo);
                    }
                });

            }
        });
    }

    /**
     * 获取到的服务器的数据更新UI
     *
     * @param registerResultInfo
     */
    private void doRegisterUpdateUI(RegisterResultInfo registerResultInfo) {
        if (registerResultInfo.getErrorCode() == RetrofitConstants.ERROR_CODE_SUCCESS) {
            ToastUtils.showToast(getActivity(), R.string.register_success);
            UiUtils.transFragment(getActivity(), BaseUtils.TAG_LOGIN, MainActivity.class);
        } else {
            ToastUtils.showToast(getActivity(), registerResultInfo.getErrorMsg());
        }
    }

    @Override
    public View getResultView() {
        return registerBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_btn:
                doRegisterOperation();
                break;
        }

    }

    /**
     * 做注册的操作
     */
    private void doRegisterOperation() {
        String username = registerBinding.registerUserEdt.getText().toString().trim();
        String password = registerBinding.registerPasswordEdt.getText().toString().trim();
        String repassword = registerBinding.registerRepasswordEdt.getText().toString().trim();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repassword)) {
            mRegisterViewModel.register(getActivity(), new RegisterInfo(username, password, repassword));
        } else {
            ToastUtils.showToast(getActivity(), R.string.user_or_pass_is_null);
        }
    }
}
