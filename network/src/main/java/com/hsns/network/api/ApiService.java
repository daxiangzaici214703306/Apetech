package com.hsns.network.api;

import com.hsns.base.bean.BannerInfo;
import com.hsns.base.bean.BaseBean;
import com.hsns.base.bean.CoinRankInfo;
import com.hsns.base.bean.HierarchyInfo;
import com.hsns.base.bean.HomeInfo;
import com.hsns.base.bean.LoginResultInfo;
import com.hsns.base.bean.NaviInfo;
import com.hsns.base.bean.PersonCoinInfo;
import com.hsns.base.bean.ProjectInfo;
import com.hsns.base.bean.RegisterResultInfo;
import com.hsns.base.bean.UserProfileInfo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    /**
     * 请求得到用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 请求结果
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginResultInfo> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册接口
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 再次输入密码
     * @return 请求结果
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<RegisterResultInfo> register(@Field(("username")) String username, @Field("password") String password, @Field("repassword") String repassword);


    /**
     * 获取文章列表
     *
     * @param page 第几页
     * @return 第几页的文章列表
     */
    @GET("article/list/{pageNum}/json")
    Observable<HomeInfo> getHomeDataByPage(@Path("pageNum") int page);


    /**
     * 获取体系数据
     *
     * @return 体系数据
     */
    @GET("tree/json")
    Observable<HierarchyInfo> getHierarchyInfo();


    /**
     * 获取导航数据
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<NaviInfo> getNaviInfo();


    /**
     * 获取cid为294的项目数据("完整项目"数据)
     *
     * @return 项目数据
     */
    @GET("project/list/{pageNum}/json?cid=294")
    Observable<ProjectInfo> getProjectInfo(@Path("pageNum") int pageNum);


    /**
     * 获取个人中心数据
     *
     * @return 个人中心数据
     */
    @GET("user/lg/userinfo/json")
    Observable<UserProfileInfo> getUserProfileInfo();


    /**
     * 获取退出登陆数据
     *
     * @return 退出登陆数据
     */
    @GET("user/logout/json")
    Observable<BaseBean<Object>> getLogoutInfo();


    /**
     * 获取Banner数据
     *
     * @return Banner数据
     */
    @GET("banner/json")
    Observable<BannerInfo> getBannerInfo();

    /**
     * 获取积分排名数据
     *
     * @return 积分排名数据
     */
    @GET("coin/rank/{num}/json")
    Observable<CoinRankInfo> getCoinRankInfo(@Path("num") int pageNum);


    /**
     * 获取个人积分排名数据
     *
     * @return 个人积分排名数据
     */
    @GET("lg/coin/list/{num}/json")
    Observable<PersonCoinInfo> getPersonCoinInfo(@Path("num") int pageNum);
}
