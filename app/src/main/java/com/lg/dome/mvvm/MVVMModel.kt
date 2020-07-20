package com.lg.dome.mvvm

import androidx.databinding.ObservableField
import java.util.*

/**
 * Created by lqj on 2020/7/20.
 */
class MVVMModel {
    var account =
        ObservableField<Account>()

    //模拟查询账号数据
    fun getAccountData(accountName: String?, callback: MCallback) {
        val random = Random()
        // boolean isSuccess = random.nextBoolean();
//  if(isSuccess){
        val account = Account()
        account.name = accountName
        account.level = random.nextInt()
        callback.onSuccess(account)
        //  }else {
//     callback.onFailed();
// }
    }
}