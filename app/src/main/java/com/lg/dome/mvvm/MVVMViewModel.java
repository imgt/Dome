package com.lg.dome.mvvm;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import com.lg.dome.BR;
import com.lg.dome.databinding.ActivityMvvmBinding;
import com.lg.dome.mvc.MvcActivity;
import com.lg.dome.mvp.ui.MvpActivity;
import com.lg.dome.mvpcontract.MvpsActivity;
import com.lg.dome.mvvms.MvvmActivity;

/**
 * Created by lqj on 2020/7/20.
 */
public class MVVMViewModel extends BaseObservable {

    private ActivityMvvmBinding binding;
    private MVVMModel mvvmModel;
    private Context context;
    private String Input;
    private String result;
    private boolean show;

    @Bindable
    public String getResult() {
        return result;
    }
    @Bindable
    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
        notifyPropertyChanged(BR.show);
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }
    //    一般需要传入Application对象，方便在ViewModel中使用application
    //    比如sharedpreferences需要使用
    public MVVMViewModel(Context context, ActivityMvvmBinding binding) {
        this.binding=binding;
        this.context=context;
        mvvmModel = new MVVMModel();

    }
    public void go(View view){
        context.startActivity(new Intent(context, MvcActivity.class));
    }
    public void mvp(View view){
        context.startActivity(new Intent(context, MvpActivity.class));
    }

    public void mvps(View view){
        context.startActivity(new Intent(context, MvpsActivity.class));
    }
    public void mvvm(View view){
        context.startActivity(new Intent(context, MvvmActivity.class));
    }

    public void getData(View view){
        setShow(true);
        Input = binding.etAccount.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                mvvmModel.getAccountData(Input, new MCallback() {
                    @Override
                    public void onSuccess(Account account) {
                        String info = account.getName() + "|" + account.getLevel();
                        setShow(false);
                        setResult(info);
                    }

                    @Override
                    public void onFailed() {
                        setResult("消息获取失败");
                    }
                });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
