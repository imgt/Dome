package com.lg.dome.mvp.presenter;

/**
 * Created by lqj on 2020/7/20.
 */
public interface OnLoginFinishedListener {
    void onUsernameError();

    void onPasswordError();

    void onSuccess(String str);
}
