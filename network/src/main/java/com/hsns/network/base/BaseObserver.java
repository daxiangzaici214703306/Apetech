package com.hsns.network.base;

import com.hsns.base.bean.BaseBean;

import io.reactivex.observers.ResourceObserver;

public class BaseObserver<T extends BaseBean> extends ResourceObserver<T>{
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
