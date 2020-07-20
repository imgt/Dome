package com.lg.dome.mvp.presenter.impl;

import com.lg.dome.mvp.model.LoginModel;
import com.lg.dome.mvp.model.bean.User;
import com.lg.dome.mvp.model.impl.LoginModelImpl;
import com.lg.dome.mvp.presenter.LoginPresenter;
import com.lg.dome.mvp.presenter.OnLoginFinishedListener;
import com.lg.dome.mvp.view.LoginView;

/**
 * Created by lqj on 2020/7/20.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(User user) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel.login(user, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess(String str) {
        if (loginView != null) {
            loginView.showSuccess(str);
        }
    }
}
