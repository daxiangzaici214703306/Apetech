package com.hsns.picture.main.view;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.hsns.base.bean.BaseBean;
import com.hsns.base.manager.PageChangeManger;
import com.hsns.base.utils.SharePreUtils;
import com.hsns.base.utils.ToastUtils;
import com.hsns.base.view.BaseActivity;
import com.hsns.base.utils.BaseUtils;
import com.hsns.network.base.BaseObserver;
import com.hsns.network.constant.RetrofitConstants;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.databinding.ActivityMainBinding;
import com.hsns.picture.home.view.HierarchyFragment;
import com.hsns.picture.home.view.HomePageFragment;
import com.hsns.picture.home.view.NaviFragment;
import com.hsns.picture.home.view.ProjectFragment;
import com.hsns.picture.login.view.LoginFragment;
import com.hsns.picture.main.viewmodel.MainViewModel;
import com.hsns.picture.photos.view.PhotoFragment;
import com.hsns.picture.register.view.RegisterFragment;
import com.hsns.picture.userprofile.view.UserProfileFragment;
import com.hsns.picture.webview.WebViewFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding activityMainBinding;
    private LoginFragment mLoginFragment;
    private PhotoFragment mPhotoFragment;
    private HomePageFragment mHomeFragment;
    private RegisterFragment mRegisterFragment;
    private HierarchyFragment mHierarchyFragment;
    private WebViewFragment mWebViewFragment;
    private NaviFragment mNaviFragment;
    private ProjectFragment mProjectFragment;
    private UserProfileFragment mUserProfileFragment;
    private TextView slideWelTex;
    private static final String TAG = "MainActivity";
    private String currentBottomTag = BaseUtils.TAG_HOME;
    private MainViewModel mMainViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initFragment();
        PageChangeManger.getInstance().setPageChangeListener(mWebViewFragment);
        initSlideLayout();
        initFragmentLoad();
        initLoginStatus();
        initViewModelAndDataListener();

    }

    private void initFragment() {
        mLoginFragment = new LoginFragment();
        mPhotoFragment = new PhotoFragment();
        mRegisterFragment = new RegisterFragment();
        mHomeFragment = new HomePageFragment();
        mHierarchyFragment = new HierarchyFragment();
        mWebViewFragment = new WebViewFragment();
        mNaviFragment = new NaviFragment();
        mProjectFragment = new ProjectFragment();
        mUserProfileFragment = new UserProfileFragment();
    }

    /**
     * 初始化viewmodel并且做数据的监听操作
     */
    private void initViewModelAndDataListener() {
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        mMainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        setDataListener();
    }

    private void setDataListener() {
        mMainViewModel.getMainDatas().observe(this, new Observer<BaseBean<Object>>() {
            @Override
            public void onChanged(BaseBean<Object> objectBaseBean) {
                if (objectBaseBean != null) {
                    Log.d(TAG, "get logout data ==>" + objectBaseBean.toString());
                    if (objectBaseBean.getErrorCode() == RetrofitConstants.ERROR_CODE_SUCCESS) {
                        doLogoutByCallback();
                    }
                }
            }
        });
    }

    /**
     * 初始化登陆状态
     */
    private void initLoginStatus() {
        if (SharePreUtils.isLogin(PictureApplication.getApplication())) {
            slideWelTex.setText(getString(R.string.welcome) + SharePreUtils.getCurLoginInfo(PictureApplication.getApplication()));
            BaseUtils.isLoginSuccess = true;
            activityMainBinding.mainNav.getMenu().findItem(R.id.nav_logout).setVisible(true);
        } else {
            slideWelTex.setText(getString(R.string.no_login));
            BaseUtils.isLoginSuccess = false;
            activityMainBinding.mainNav.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }
    }

    /**
     * 初始化Fragment的加载
     */
    private void initFragmentLoad() {
        activityMainBinding.ryContainer.post(new Runnable() {
            @Override
            public void run() {
                tranFragment(tag);
            }
        });
    }

    /**
     * 初始化侧滑栏
     */
    private void initSlideLayout() {
        activityMainBinding.mainBottomNav.setOnNavigationItemSelectedListener(this);
        activityMainBinding.mainNav.setNavigationItemSelectedListener(this);
        slideWelTex = (TextView) activityMainBinding.mainNav.getHeaderView(0).findViewById(R.id.text_welcome);
        activityMainBinding.mainNav.getHeaderView(0).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.slide_layout:
                if (!BaseUtils.isLoginSuccess) {
                    tranFragment(BaseUtils.TAG_LOGIN);
                }
                break;
        }
    }

    /**
     * 注销操作
     */
    private void doLogout() {
        if (BaseUtils.isLoginSuccess) {
            mMainViewModel.logout();
        } else {
            ToastUtils.showToast(this, R.string.no_login);
        }
    }

    /**
     * 通过退出网络请求回调做相应view的设置和登陆信息的初始化
     */
    private void doLogoutByCallback() {
        ToastUtils.showToast(this, R.string.logoff_success);
        slideWelTex.setText(getString(R.string.login));
        activityMainBinding.slideContainer.closeDrawers();
        SharePreUtils.clearLoginStatus(this);
        SharePreUtils.storeCookie(this, "");//清楚cookies
        BaseUtils.isLoginSuccess = false;
        tranFragment(BaseUtils.TAG_LOGIN);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
        initLoginStatus();
        tranFragmentByNewIntent(intent);

    }

    /**
     * newintent的回调信息处理
     * @param intent 数据对象
     */
    private void tranFragmentByNewIntent(Intent intent) {
        if (intent != null) {
            String tag = intent.getStringExtra(BaseUtils.TAG_FRAGMENT);
            if (!TextUtils.isEmpty(tag) && tag.equals(BaseUtils.TAG_BACK)) {
                //记录底部item的位置做对应fragment的加载
                tag = currentBottomTag;
            }
            tranFragment(tag);
        }

    }

    /**
     * 根据tag切换到对应的fragment
     *
     * @param tag tag值
     */
    private void tranFragment(String tag) {
        Log.d(TAG, "tranFragment tag==>" + tag);
        activityMainBinding.mainBottomNav.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(tag)) return;
        switch (tag) {
            case BaseUtils.TAG_LOGIN:
                activityMainBinding.slideContainer.closeDrawers();
                activityMainBinding.mainBottomNav.setVisibility(View.GONE);
                showFragment(R.id.ry_container, mLoginFragment, BaseUtils.TAG_LOGIN);
                break;
            case BaseUtils.TAG_PHOTOS:
                showFragment(R.id.ry_container, mPhotoFragment, BaseUtils.TAG_PHOTOS);
                break;
            case BaseUtils.TAG_USERPROFILE:
                activityMainBinding.slideContainer.closeDrawers();
                activityMainBinding.mainBottomNav.setVisibility(View.GONE);
                showFragment(R.id.ry_container, mUserProfileFragment, BaseUtils.TAG_USERPROFILE);
                break;
            case BaseUtils.TAG_SETTING:
                break;
            case BaseUtils.TAG_REGISTER:
                showFragment(R.id.ry_container, mRegisterFragment, BaseUtils.TAG_REGISTER);
                activityMainBinding.mainBottomNav.setVisibility(View.GONE);
                break;
            case BaseUtils.TAG_HOME:
                currentBottomTag = BaseUtils.TAG_HOME;
                showFragment(R.id.ry_container, mHomeFragment, BaseUtils.TAG_HOME);
                break;
            case BaseUtils.TAG_NAVI:
                currentBottomTag = BaseUtils.TAG_NAVI;
                showFragment(R.id.ry_container, mNaviFragment, BaseUtils.TAG_NAVI);
                break;
            case BaseUtils.TAG_SYSTEM:
                currentBottomTag = BaseUtils.TAG_SYSTEM;
                showFragment(R.id.ry_container, mHierarchyFragment, BaseUtils.TAG_SYSTEM);
                break;
            case BaseUtils.TAG_PROJECT:
                currentBottomTag = BaseUtils.TAG_PROJECT;
                showFragment(R.id.ry_container, mProjectFragment, BaseUtils.TAG_PROJECT);
                break;
            case BaseUtils.TAG_WEBVIEW:
                showFragment(R.id.ry_container, mWebViewFragment, BaseUtils.TAG_WEBVIEW);
                activityMainBinding.mainBottomNav.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_setting:
                ToastUtils.showToast(this, R.string.function_not_imp);
                break;
            case R.id.nav_userprofile:
                if (!BaseUtils.isLoginSuccess) {
                    tranFragment(BaseUtils.TAG_LOGIN);
                    return true;
                }
                tranFragment(BaseUtils.TAG_USERPROFILE);
                break;
            case R.id.nav_update:
                ToastUtils.showToast(this, R.string.latest_version);
                break;
            case R.id.nav_logout:
                doLogout();
                break;
            case R.id.nav_bottom_home:
                tranFragment(BaseUtils.TAG_HOME);
                break;
            case R.id.nav_bottom_navi:
                tranFragment(BaseUtils.TAG_NAVI);
                break;
            case R.id.nav_bottom_system:
                tranFragment(BaseUtils.TAG_SYSTEM);
                break;
            case R.id.nav_bottom_project:
                tranFragment(BaseUtils.TAG_PROJECT);
                break;
        }
        return true;
    }

}
