package com.lg.dome.mvvms.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lqj on 2020/7/21.
 */
public  abstract class BaseObserver<T> implements Observer<ResultData<T>> {
   // private ResultData<T>  resultData;
    @Override
    public void onSubscribe(Disposable d) {
        onDisposable(d);
    }
    @Override
    public void onNext(ResultData<T> response) {
        if(response.getStatus()==1){
            onSuccess(response.getData());
        }else{
            onFailure(null,response.getData().toString(),response.getStatus());
        }
    }
    @Override
    public void onError(Throwable e) {
        onFailure(e,"请求失败！",0);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T demo);
    public abstract void onDisposable(Disposable d);
    public abstract void onFailure(Throwable e,String errorMsg,int code);
}
