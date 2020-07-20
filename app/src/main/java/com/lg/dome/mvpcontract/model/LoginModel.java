package com.lg.dome.mvpcontract.model;

import android.os.Handler;
import android.text.TextUtils;

import com.lg.dome.mvp.model.bean.User;
import com.lg.dome.mvp.presenter.OnLoginFinishedListener;
import com.lg.dome.mvpcontract.contract.LoginContract;

/**
 * Created by lqj on 2020/7/20.
 */
public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void login(User user, final OnLoginFinishedListener listener) {
        final String username = user.getUsername();
        final String password = user.getPassword();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();//model层里面回调listener
                    error = true;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess(user.toString());
                }
            }
        }, 2000);
    }
}
