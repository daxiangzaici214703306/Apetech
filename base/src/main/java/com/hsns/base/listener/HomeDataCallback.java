package com.hsns.base.listener;

import com.hsns.base.bean.HierarchyInfo;
import com.hsns.base.bean.HomeInfo;
import com.hsns.base.bean.NaviInfo;
import com.hsns.base.bean.ProjectInfo;

public interface HomeDataCallback {
    /**
     * 获取到的Home选项页面的文章数据回调信息
     */
    void onHomeDataCallback(HomeInfo mHomeInfo);

    /**
     * 获取到的体系页面的数据回调信息
     */
    void onHierarchyDataCallback(HierarchyInfo mHierarchyInfo);

    /**
     * 获取到的导航页面的数据回调信息
     */
    void onNaviDataCallback(NaviInfo mNaviInfo);

    /**
     * 获取数据回调给view
     * @param mProjectInfo 数据对象
     */
    void onProjectDataCallback(ProjectInfo mProjectInfo);
}
