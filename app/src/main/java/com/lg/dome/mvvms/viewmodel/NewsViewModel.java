package com.lg.dome.mvvms.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lg.dome.mvvms.api.BaseObserver;
import com.lg.dome.mvvms.api.DataState;
import com.lg.dome.mvvms.api.NetManager;
import com.lg.dome.mvvms.api.ResultData;

import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lqj on 2020/7/20.
 */
public class NewsViewModel extends BaseViewModel {
    /**
     * 当数据请求成功回调
     */
    protected MutableLiveData<NewsBean> news = new MutableLiveData<>();
    protected MutableLiveData<DataState<List<DataBean>>> result = new MutableLiveData<>();
    /**
     * 请求网络数据	success
     */
    public void requestData() {
      //  showDialog.setValue(true, "加载中");
        Disposable disposable = NetManager.getInstance().news()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {
                     //   showDialog.setValue(false);
                        news.setValue(newsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       // showDialog.setValue(false);
                        /*
                         * 发生了错误，通知UI层
                         */
                        error.setValue("发生错误了");
                    }
                });
        addDisposable(disposable);
    }

    public void getData() {

        Observable<ResultData<List<DataBean>>> observable = NetManager.getToInstance().art();
        setSubscribe(observable,new BaseObserver<List<DataBean>>(){
            @Override
            public void onSuccess(List<DataBean> demo) {
                DataState<List<DataBean>> data=  new DataState<>();
                data.setData(demo);
                data.setSuccess(true);
                result.postValue(data);
            }
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }
            @Override
            public void onFailure(Throwable e, String errorMsg,int code) {
                DataState<List<DataBean>> data= new DataState<>();
                data.setSuccess(false);
                data.setCod(code);
                data.setError(errorMsg);
                result.postValue(data);
            }
        });
//        Observable<String> observable = NetManager.getInstance().newstr();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                       // addDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(String value) {
//                       // bean[0] = value;
//                        Log.e("---",value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
////                        if (iView != null) {
////                            iView.onError("失败");
////                        }
//                    }
//                    @Override
//                    public void onComplete() {
//
////                        if (iView != null) {
////                            iView.onSuccess(bean[0]);
////                        }
//                    }
//                });
    }

    public MutableLiveData<NewsBean> getNews() {
        return news;
    }

    public MutableLiveData<DataState<List<DataBean>>> getResult() {
        return result;
    }
}
