package com.hsns.network.manager;

import android.content.Context;
import android.util.Log;

import com.hsns.base.bean.BannerInfo;
import com.hsns.base.bean.BaseBean;
import com.hsns.base.bean.CoinRankInfo;
import com.hsns.base.bean.HierarchyInfo;
import com.hsns.base.bean.HomeInfo;
import com.hsns.base.bean.LoginResultInfo;
import com.hsns.base.bean.NaviInfo;
import com.hsns.base.bean.PersonCoinInfo;
import com.hsns.base.bean.ProjectInfo;
import com.hsns.base.bean.RegisterInfo;
import com.hsns.base.bean.RegisterResultInfo;
import com.hsns.base.bean.UserInfo;
import com.hsns.base.bean.UserProfileInfo;
import com.hsns.base.listener.HomeDataCallback;
import com.hsns.base.listener.LoginCallback;
import com.hsns.base.listener.LogoutCallback;
import com.hsns.base.listener.RegisterCallback;
import com.hsns.base.listener.UserProfileCallback;
import com.hsns.network.api.ApiService;
import com.hsns.network.compoent.DefaultClientComponent;
import com.hsns.network.constant.RetrofitConstants;
import com.hsns.network.base.BaseRequset;
import com.hsns.network.provider.OkHttpClientProvider;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    /**
     * 单例模式
     **/
    private static volatile HttpManager mHttpManager = null;
    private Retrofit retrofit;
    private ApiService service;
    private static final String TAG = "HttpManager";


    /**
     * 构造函数私有化
     **/
    private HttpManager(Context context) {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(RetrofitConstants.BASE_URL)
                .client(OkHttpClientProvider.provideOkHttpClient(context, new DefaultClientComponent(context)))
                .build();
        service = retrofit.create(ApiService.class);
    }

    /**
     * 公有的静态函数，对外暴露获取单例对象的接口
     **/
    public static HttpManager getInstance(Context context) {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager(context);
                }
            }
        }
        return mHttpManager;
    }


    /**
     * 请求获取用户信息
     *
     * @param info          传入的值
     * @param loginCallback 用户信息回调
     */
    public synchronized void requestUserInfo(final UserInfo info, final LoginCallback loginCallback) {
        new BaseRequset<LoginResultInfo>().requestNet(service.login(info.getUsername(), info.getPassword()),
                new BaseRequset.BaseListener<LoginResultInfo>() {
                    @Override
                    public void onResult(LoginResultInfo loginResultInfo) {
                        if (loginCallback != null) {
                            loginCallback.onLoginCallback(loginResultInfo);
                        }
                    }
                });
    }

    /**
     * 注册用户
     *
     * @param registerInfo 注册的信息实体类
     */
    public synchronized void registerUser(final RegisterInfo registerInfo, final RegisterCallback registerCallback) {
        new BaseRequset<RegisterResultInfo>().requestNet(service.register(registerInfo.getUsername(), registerInfo.getPassword(), registerInfo.getRepassword()), new BaseRequset.BaseListener<RegisterResultInfo>() {
            @Override
            public void onResult(RegisterResultInfo registerResultInfo) {
                if (registerCallback != null) {
                    registerCallback.onRegisterCallback(registerResultInfo);
                }
            }
        });
    }


    /**
     * 请求获取文章信息
     *
     * @param pageNum           页面位置
     * @param mHomeDataCallback 获取数据回调
     */
    public synchronized void requestHomeInfo(int pageNum, final HomeDataCallback mHomeDataCallback) {
        new BaseRequset<HomeInfo>().requestNet(service.getHomeDataByPage(pageNum), new BaseRequset.BaseListener<HomeInfo>() {
            @Override
            public void onResult(HomeInfo homeInfo) {
                if (mHomeDataCallback != null) {
                    mHomeDataCallback.onHomeDataCallback(homeInfo);
                }
            }
        });


//调试用
//        Call<HomeInfo> o=service.getHomeDataByPage(pageNum);
//        o.enqueue(new Callback<HomeInfo>() {
//            @Override
//            public void onResponse(Call<HomeInfo> call, Response<HomeInfo> response) {
//                Log.i(TAG,response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<HomeInfo> call, Throwable t) {
//
//            }
//        });
    }

    /**
     * 获取体系信息
     */
    public synchronized void requestHieerarchyInfo(final HomeDataCallback mHomeDataCallback) {
        new BaseRequset<HierarchyInfo>().requestNet(service.getHierarchyInfo(), new BaseRequset.BaseListener<HierarchyInfo>() {
            @Override
            public void onResult(HierarchyInfo hierarchyInfo) {
                if (mHomeDataCallback != null) {
                    mHomeDataCallback.onHierarchyDataCallback(hierarchyInfo);
                }
            }
        });
    }


    /**
     * 获取导航信息
     */
    public synchronized void requestNaviInfo(final HomeDataCallback mHomeDataCallback) {
        new BaseRequset<NaviInfo>().requestNet(service.getNaviInfo(), new BaseRequset.BaseListener<NaviInfo>() {
            @Override
            public void onResult(NaviInfo naviInfo) {
                if (mHomeDataCallback != null) {
                    mHomeDataCallback.onNaviDataCallback(naviInfo);
                }
            }
        });
    }

    /**
     * 获取完整项目数据
     *
     * @param pageNum           第几页
     * @param mHomeDataCallback 数据回调
     */
    public synchronized void requestProjectInfo(int pageNum, final HomeDataCallback mHomeDataCallback) {
        new BaseRequset<ProjectInfo>().requestNet(service.getProjectInfo(pageNum), new BaseRequset.BaseListener<ProjectInfo>() {
            @Override
            public void onResult(ProjectInfo projectInfo) {
//                Log.d(TAG,"onResult projectInfo==>"+projectInfo.toString());
                if (mHomeDataCallback != null) {
                    mHomeDataCallback.onProjectDataCallback(projectInfo);
                }
            }
        });
    }


    /**
     * 获取个信息数据
     *
     * @param mUserProfileCallback 数据回调
     */
    public synchronized void requestUserProfileInfo(final UserProfileCallback mUserProfileCallback) {
        new BaseRequset<UserProfileInfo>().requestNet(service.getUserProfileInfo(), new BaseRequset.BaseListener<UserProfileInfo>() {
            @Override
            public void onResult(UserProfileInfo userProfileInfo) {
                if (mUserProfileCallback != null) {
                    mUserProfileCallback.onUserProfileInfoCallback(userProfileInfo);
                }
            }
        });

//        //调试用
//        Call<UserProfileInfo> o=service.getUserProfileInfo();
//        o.enqueue(new Callback<UserProfileInfo>() {
//            @Override
//            public void onResponse(Call<UserProfileInfo> call, Response<UserProfileInfo> response) {
//                Log.i(TAG,response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<UserProfileInfo> call, Throwable t) {
//
//            }
//        });
    }


    /**
     * 请求Banner数据
     *
     * @param mHomeDataCallback 数据回调
     */
    public synchronized void requestBannerData(final HomeDataCallback mHomeDataCallback) {
        new BaseRequset<BannerInfo>().requestNet(service.getBannerInfo(), new BaseRequset.BaseListener<BannerInfo>() {
            @Override
            public void onResult(BannerInfo mBannerInfo) {
                if (mHomeDataCallback != null) {
                    mHomeDataCallback.onBannerDataCallback(mBannerInfo);
                }
            }
        });
    }

    /**
     * 退出登陆请求
     *
     * @param mLogOutCallback 数据回调
     */
    public synchronized void requestLogout(final LogoutCallback mLogOutCallback) {
        new BaseRequset<BaseBean<Object>>().requestNet(service.getLogoutInfo(), new BaseRequset.BaseListener<BaseBean<Object>>() {
            @Override
            public void onResult(BaseBean<Object> mLogoutBackInfo) {
                if (mLogOutCallback != null) {
                    mLogOutCallback.onLogoutCallback(mLogoutBackInfo);
                }
            }
        });
    }


    /**
     * 积分排名请求
     *
     * @param mUserProfileCallback 积分排名数据回调
     */
    public synchronized void requestCoinInfo(final int pageNum, final UserProfileCallback mUserProfileCallback) {
        new BaseRequset<CoinRankInfo>().requestNet(service.getCoinRankInfo(pageNum), new BaseRequset.BaseListener<CoinRankInfo>() {
            @Override
            public void onResult(CoinRankInfo mCoinRankInfo) {
                if (mUserProfileCallback != null) {
                    mUserProfileCallback.onCoinRankCallback(mCoinRankInfo);
                }
            }
        });
    }

    /**
     * 个人积分排名请求
     *
     * @param mUserProfileCallback 个人积分排名数据回调
     */
    public synchronized void requestPersonCoinInfo(final int pageNum, final UserProfileCallback mUserProfileCallback) {
        new BaseRequset<PersonCoinInfo>().requestNet(service.getPersonCoinInfo(pageNum), new BaseRequset.BaseListener<PersonCoinInfo>() {
            @Override
            public void onResult(PersonCoinInfo mPersonCoinInfo) {
                if (mUserProfileCallback != null) {
                    mUserProfileCallback.onPersonCoinInfoCallback(mPersonCoinInfo);
                }
            }
        });
    }

}
