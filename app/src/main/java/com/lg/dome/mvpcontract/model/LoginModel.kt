package com.lg.dome.mvpcontract.model

import android.os.Handler
import android.text.TextUtils
import com.lg.dome.mvp.model.bean.User
import com.lg.dome.mvp.presenter.OnLoginFinishedListener
import com.lg.dome.mvpcontract.contract.LoginContract.ILoginModel

/**
 * Created by lqj on 2020/7/20.
 */
class LoginModel : ILoginModel {
    override fun login(user: User?, listener: OnLoginFinishedListener?) {
        val username = user?.username
        val password = user?.password
        Handler().postDelayed({
            var error = false
            if (TextUtils.isEmpty(username)) {
                listener!!.onUsernameError() //model层里面回调listener
                error = true
            }
            if (TextUtils.isEmpty(password)) {
                listener!!.onPasswordError()
                error = true
            }
            if (!error) {
                listener!!.onSuccess(user.toString())
            }
        }, 2000)
    }
}