package com.lg.dome.mvp.presenter;

import com.lg.dome.mvp.model.bean.User;

/**
 * Created by lqj on 2020/7/20.
 */
public interface LoginPresenter {
    void validateCredentials(User user);

    void onDestroy();
}
