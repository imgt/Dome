package com.lg.dome.mvvm

/**
 * Created by lqj on 2020/7/20.
 */
interface MCallback {
    fun onSuccess(account: Account?)
    fun onFailed()
}