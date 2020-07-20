package com.lg.dome.mvp.model;

import com.lg.dome.mvp.presenter.OnLoginFinishedListener;
import com.lg.dome.mvp.model.bean.User;

/**
 * Created by lqj on 2020/7/20.
 */
public interface LoginModel {
    void login(User user, OnLoginFinishedListener listener);
}
