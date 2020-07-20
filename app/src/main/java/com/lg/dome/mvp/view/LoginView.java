package com.lg.dome.mvp.view;

/**
 * Created by lqj on 2020/7/20.
 */
public interface LoginView {
    //login是个耗时操作，我们需要给用户一个友好的提示，一般就是操作ProgressBar
    void showProgress();

    void hideProgress();
    //login当然存在登录成功与失败的处理，失败给出提示
    void setUsernameError();

    void setPasswordError();
    //login成功，也给个提示
    void showSuccess(String str);
}
