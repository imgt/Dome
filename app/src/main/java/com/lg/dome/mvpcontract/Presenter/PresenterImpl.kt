package com.lg.dome.mvpcontract.Presenter

import com.lg.dome.mvp.model.bean.User
import com.lg.dome.mvp.presenter.OnLoginFinishedListener
import com.lg.dome.mvpcontract.contract.LoginContract.*
import com.lg.dome.mvpcontract.model.LoginModel

/**
 * Created by lqj on 2020/7/20.
 */
class PresenterImpl(private var loginView: ILoginView?) : ILoginPresenter, OnLoginFinishedListener {
    private val loginModel: ILoginModel=LoginModel()
//    init {
//        loginModel = LoginModel()
//    }
    override fun validateCredentials(user: User?) {
        if (loginView != null) {
            loginView!!.showProgress()
        }
        loginModel.login(user, this)
    }

    override fun onDestroy() {
        loginView = null
    }

    override fun onUsernameError() {
        if (loginView != null) {
            loginView!!.setUsernameError()
            loginView!!.hideProgress()
        }
    }

    override fun onPasswordError() {
        if (loginView != null) {
            loginView!!.setPasswordError()
            loginView!!.hideProgress()
        }
    }

    override fun onSuccess(str: String) {
        if (loginView != null) {
            loginView!!.showSuccess(str)
        }
    }


}