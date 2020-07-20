package com.lg.dome.mvp.model.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.lg.dome.mvp.model.LoginModel;
import com.lg.dome.mvp.presenter.OnLoginFinishedListener;
import com.lg.dome.mvp.model.bean.User;

/**
 * Created by lqj on 2020/7/20.
 */
public class LoginModelImpl implements LoginModel {
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
