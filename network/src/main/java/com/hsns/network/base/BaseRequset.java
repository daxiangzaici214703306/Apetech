package com.hsns.network.base;

import com.hsns.base.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class BaseRequset<T extends BaseBean> {
    /**
     * 请求网络
     *
     * @param observable   传入的对象
     * @param baseListener 回调监听
     */
    public void requestNet(Observable<T> observable, BaseListener<T> baseListener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        super.onNext(t);
                        if (baseListener != null) {
                            baseListener.onResult(t);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (baseListener != null) {
                            baseListener.onResult(null);
                        }
                    }
                });
    }

    /**
     * 封装网络请求后的回调
     *
     * @param <T> 传值
     */
    public interface BaseListener<T extends BaseBean> {
        /**
         * 正确请求的结果
         *
         * @param t 结果内容
         */
        void onResult(T t);
    }

}
