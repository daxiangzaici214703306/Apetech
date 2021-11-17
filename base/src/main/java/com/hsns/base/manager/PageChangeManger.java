package com.hsns.base.manager;

import com.hsns.base.listener.PageChangeListener;

public class PageChangeManger {
    /**
     * 单例模式
     **/
    private static volatile PageChangeManger mPageChangeManger = null;
    private PageChangeListener listener;

    /**
     * 构造函数私有化
     **/
    private PageChangeManger() {
    }

    /**
     * 公有的静态函数，对外暴露获取单例对象的接口
     **/
    public static PageChangeManger getInstance() {
        if (mPageChangeManger == null) {
            synchronized (PageChangeManger.class) {
                if (mPageChangeManger == null) {
                    mPageChangeManger = new PageChangeManger();
                }
            }
        }
        return mPageChangeManger;
    }


    public void setPageChangeListener(PageChangeListener listener) {
        this.listener = listener;
    }


    public PageChangeListener getListener() {
        return listener;
    }

}
