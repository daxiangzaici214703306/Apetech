package com.hsns.picture.home.model;

public interface IHomeModel {
    /**
     * 请求文章第几页的数据
     * @param num 第几页
     */
    void requestWhichPageData(int num);

    /**
     * 获取体系数据
     */
    void requsetHierarchyData();

    /**
     * 获取导航数据
     */
    void requestNaviData();

    /**
     * 获取完整项目数据
     * @param num 第几页
     */
    void requestProjectData(int num);
}
