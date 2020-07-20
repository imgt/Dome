package com.lg.dome.mvpcontract.contract

import com.lg.dome.mvp.model.bean.User
import com.lg.dome.mvp.presenter.OnLoginFinishedListener

/**
 * Created by lqj on 2020/7/20.
 */
interface LoginContract {
    interface ILoginModel {
        fun login(user: User?, listener: OnLoginFinishedListener?)
    }

    interface ILoginPresenter {
        fun validateCredentials(user: User?)
        fun onDestroy()
    }

    interface ILoginView {
        fun showProgress()
        fun hideProgress()
        //login当然存在登录成功与失败的处理，失败给出提示
        fun setUsernameError()
        fun setPasswordError()
        //login成功，也给个提示
        fun showSuccess(str: String?)
    }
}