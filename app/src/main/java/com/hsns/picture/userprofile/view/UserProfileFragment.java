package com.hsns.picture.userprofile.view;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hsns.base.bean.UserProfileInfo;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.ToastUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.base.view.BaseFragment;
import com.hsns.network.constant.RetrofitConstants;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.databinding.FragmentUserprofileBinding;
import com.hsns.picture.userprofile.viewmodel.UserProfileViewModel;

public class UserProfileFragment extends BaseFragment {
    private FragmentUserprofileBinding fragmentUserprofileBinding;
    private UserProfileViewModel mUserProfileViewModel;
    private static final String TAG = "UserProfileFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_userprofile;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        fragmentUserprofileBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        mUserProfileViewModel = new ViewModelProvider(this, factory).get(UserProfileViewModel.class);
        setUpDataListener();
        initData();
        backListener();
    }

    private void initData() {
        fragmentUserprofileBinding.upRank.post(new Runnable() {
            @Override
            public void run() {
                mUserProfileViewModel.requestUserProfileInfo();
            }
        });
    }

    private void backListener() {
        fragmentUserprofileBinding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:
                UiUtils.transFragment(PictureApplication.getApplication(), BaseUtils.TAG_BACK, MainActivity.class);
                break;
        }
    }

    /**
     * 监听获取个人信息数据
     */
    private void setUpDataListener() {
        mUserProfileViewModel.getUpDatas().observe(this, new Observer<UserProfileInfo>() {
            @Override
            public void onChanged(UserProfileInfo userProfileInfo) {
                if(userProfileInfo==null) return;
                if (userProfileInfo.getErrorCode() == RetrofitConstants.ERROR_CODE_NOT_LOGIN) {
                    ToastUtils.showToast(PictureApplication.getApplication(), userProfileInfo.getErrorMsg());
                } else if (userProfileInfo.getErrorCode() == RetrofitConstants.ERROR_CODE_SUCCESS) {
                    Log.d(TAG, "userProfileInfo==>" + userProfileInfo.toString());
                    updateView(userProfileInfo);
                }
            }
        });
    }

    /**
     * 更新view
     *
     * @param userProfileInfo 数据
     */
    private void updateView(UserProfileInfo userProfileInfo) {
        if (userProfileInfo != null) {
            UserProfileInfo.Data data = userProfileInfo.getData();
            if (data != null) {
                UserProfileInfo.UserInfo userInfo = data.getUserInfo();
                UserProfileInfo.CoinInfo coinInfo = data.getCoinInfo();
                updateUserInfoView(userInfo);
                updateCoinInfoView(coinInfo);
            }


        }
    }

    /**
     * 更新用户信息view
     *
     * @param userInfo 个人信息
     */
    private void updateUserInfoView(UserProfileInfo.UserInfo userInfo) {
        if (userInfo == null) return;
        String userName = userInfo.getUsername();
        String nickName = userInfo.getNickname();
        String email = userInfo.getEmail();
        String icon = userInfo.getIcon();
        boolean isAdmin = userInfo.getAdmin();
        fragmentUserprofileBinding.upUsername.setText(getValue(userName, R.string.user, true));
        fragmentUserprofileBinding.upNickname.setText(getValue(nickName, R.string.nickname, true));
        fragmentUserprofileBinding.upEmail.setText(getValue(email, R.string.email, true));
//        fragmentUserprofileBinding.upIcon.setText(getValue(icon, R.string.usericon,true));
        fragmentUserprofileBinding.upAdmin.setText(PictureApplication.getApplication().getResources().getString(R.string.admin) + (isAdmin ? "yes" : "no"));
    }

    /**
     * 更新积分信息view
     *
     * @param coinInfo 积分信息
     */
    private void updateCoinInfoView(UserProfileInfo.CoinInfo coinInfo) {
        if (coinInfo == null) return;
        int coinCount = coinInfo.getCoinCount();
        int coinLevel = coinInfo.getLevel();
        String coinRank = coinInfo.getRank();
        fragmentUserprofileBinding.upCoinCount.setText(getValue(String.valueOf(coinCount), R.string.coincount, false));
        fragmentUserprofileBinding.upLevel.setText(getValue(String.valueOf(coinLevel), R.string.coinlevel, false));
        fragmentUserprofileBinding.upRank.setText(getValue(coinRank, R.string.coinrank, false));
    }

    /**
     * 获取对应的属性值
     *
     * @param content  内容
     * @param stringId 标题
     * @return
     */
    public String getValue(String content, int stringId, boolean useStringId) {
        return (useStringId ? PictureApplication.getApplication().getResources().getString(stringId) : "") + (!TextUtils.isEmpty(content) ? content : "");
    }

    @Override
    public View getResultView() {
        return fragmentUserprofileBinding.getRoot();
    }
}
