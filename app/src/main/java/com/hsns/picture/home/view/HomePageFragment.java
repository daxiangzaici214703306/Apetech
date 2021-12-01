package com.hsns.picture.home.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hsns.base.bean.BannerInfo;
import com.hsns.base.bean.HomeInfo;
import com.hsns.base.manager.PageChangeManger;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.picture.home.adapter.HomePageAdapter;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class HomePageFragment extends BaseHomeFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener,
        OnItemClickListener, BGABanner.Delegate, BGABanner.Adapter<ImageView, String>, Runnable {
    private HomePageAdapter mHomeAdapter;
    private List<HomeInfo.Datas> mHomeDatas;
    private int num = 0;//当前文章加载的页数
    private static final String TAG = "HomeFragment";
    private View bannerLayout;
    private BGABanner banner;
    private List<BannerInfo.Data> bannerInfos;

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater, container);
        setHomeDataListener();
        setBannerDataListenr();
        initBanner();
        initAdapter();
        rySetting();

    }

    /**
     * RecyclerView相关设置
     */
    private void rySetting() {
        homeBinding.homeRy.setLinearLayout();
        homeBinding.homeRy.setAdapter(mHomeAdapter);
        homeBinding.homeRy.setFooterViewText(PictureApplication.getApplication().getString(R.string.loading));
        homeBinding.homeRy.setOnPullLoadMoreListener(this);
        homeBinding.homeRy.post(this);
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        mHomeDatas = new ArrayList<>();
        mHomeAdapter = new HomePageAdapter(mHomeDatas);
        mHomeAdapter.addHeaderView(bannerLayout);
        mHomeAdapter.setOnItemClickListener(this);
    }

    /**
     * 初始化Banner
     */
    private void initBanner() {
        bannerInfos = new ArrayList<>();
        bannerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_banner, null);
        banner = bannerLayout.findViewById(R.id.banner);
        banner.setDelegate(this);
    }

    /**
     * 监听Banner数据
     */
    private void setBannerDataListenr() {
        mHomeViewModel.getBannerInfos().observe(this, new Observer<BannerInfo>() {
            @Override
            public void onChanged(BannerInfo bannerInfo) {
                Log.d(TAG, "bannerInfo==>" + bannerInfo);
                if (bannerInfo != null && bannerInfo.getData() != null && bannerInfo.getData().size() != 0) {
                    bannerInfos.clear();
                    bannerInfos.addAll(bannerInfo.getData());
                    refreshBanner();
                }
            }
        });
    }

    /**
     * 刷新banner
     */
    private void refreshBanner() {
        banner.setAutoPlayAble(bannerInfos.size() > 1);
        List<String> bannerFeedList = new ArrayList<>();
        List<String> bannerTitleList = new ArrayList<>();
        for (BannerInfo.Data data : bannerInfos) {
            bannerFeedList.add(data.getImagePath());
            bannerTitleList.add(data.getTitle());
        }
        banner.setData(bannerFeedList, bannerTitleList);
        banner.setAdapter(this);
    }


    /**
     * 监听home文章数据
     */
    private void setHomeDataListener() {
        mHomeViewModel.getHomeInfos().observe(this, new Observer<HomeInfo>() {
            @Override
            public void onChanged(HomeInfo homeInfo) {
                homeBinding.loading.setVisibility(View.GONE);
                homeBinding.homeRy.setVisibility(View.VISIBLE);
                homeBinding.homeRy.setPullLoadMoreCompleted();
                if (homeInfo != null) {
                    Log.d(TAG, "homeInfo==>" + homeInfo);
                    if (homeInfo.getData() != null && homeInfo.getData().getDatas() != null) {
                        mHomeDatas.addAll(homeInfo.getData().getDatas());
                        mHomeAdapter.notifyDataSetChanged();
                    }
                } else {
                    noDataUiSetting();
                }
            }
        });

    }

    /**
     * 没有数据时候的ui设置
     */
    private void noDataUiSetting() {
        if (mHomeDatas.size() == 0) {
            mNoDataView.setVisible(true);
            homeBinding.homeRy.setVisibility(View.GONE);
        }
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        mHomeViewModel.requestBannerData();
    }

    @Override
    public void onLoadMore() {
        num++;
        mHomeViewModel.requestWhichPageData(num);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        num = 0;
        mHomeDatas.clear();
        homeBinding.homeRy.setOnPullLoadMoreListener(null);
        homeBinding.homeRy.removeCallbacks(this);
    }

    @Override
    public void onNoDataClickCallBackByBaseHome() {
        mNoDataView.setVisible(false);
        homeBinding.loading.setVisibility(View.VISIBLE);
        homeBinding.homeRy.setVisibility(View.GONE);
        mHomeViewModel.requestWhichPageData(num);
        mHomeViewModel.requestBannerData();
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        Log.d(TAG, "onItemClick position==>" + position);
        if (mHomeDatas != null && mHomeDatas.size() > position) {
            tranToWebView(mHomeDatas.get(position).getLink(), mHomeDatas.get(position).getTitle());

        }
    }

    /**
     * 切换到WebView的操作
     *
     * @param link  链接
     * @param title 标题
     */
    private void tranToWebView(String link, String title) {
        UiUtils.transFragment(getActivity(), BaseUtils.TAG_WEBVIEW, MainActivity.class);
        PageChangeManger.getInstance().getListener().onPageChange(link, title);
    }


    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
        if (bannerInfos != null && bannerInfos.size() > position) {
            tranToWebView(bannerInfos.get(position).getUrl(), bannerInfos.get(position).getTitle());
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView imageView, @Nullable String feedImageUrl, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loading_error)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(getActivity()).load(feedImageUrl).apply(options).into(imageView);
    }

    @Override
    public void run() {
        homeBinding.loading.setVisibility(View.VISIBLE);
        homeBinding.homeRy.setVisibility(View.GONE);
        mHomeViewModel.requestBannerData();
        mHomeViewModel.requestWhichPageData(num);
    }
}
