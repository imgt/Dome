package com.lg.dome.mvpcontract.contract;

import com.lg.dome.mvp.model.bean.User;
import com.lg.dome.mvp.presenter.OnLoginFinishedListener;

/**
 * Created by lqj on 2020/7/20.
 */
public interface LoginContract {
    public interface ILoginModel{
        void login(User user, OnLoginFinishedListener listener);
    }
    public interface ILoginPresenter{
        void validateCredentials(User user);
        void onDestroy();

    }
    public interface ILoginView{
        void showProgress();
        void hideProgress();
        //login当然存在登录成功与失败的处理，失败给出提示
        void setUsernameError();
        void setPasswordError();
        //login成功，也给个提示
        void showSuccess(String str);
    }
}
