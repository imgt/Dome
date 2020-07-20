package com.lg.dome.mvpcontract.Presenter;

import com.lg.dome.mvp.model.bean.User;
import com.lg.dome.mvp.presenter.OnLoginFinishedListener;
import com.lg.dome.mvpcontract.contract.LoginContract;
import com.lg.dome.mvpcontract.model.LoginModel;

/**
 * Created by lqj on 2020/7/20.
 */
public class PresenterImpl implements LoginContract.ILoginPresenter, OnLoginFinishedListener {
    private LoginContract.ILoginView loginView;
    private LoginContract.ILoginModel loginModel;

    public PresenterImpl(LoginContract.ILoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModel();
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
