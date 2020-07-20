package com.lg.dome.mvvm;

import androidx.databinding.ObservableField;

import java.util.Random;

/**
 * Created by lqj on 2020/7/20.
 */
public class MVVMModel {
    public ObservableField<Account> account = new ObservableField<>();
    //模拟查询账号数据
    public void getAccountData(String accountName, MCallback callback){
        Random random = new Random();
       // boolean isSuccess = random.nextBoolean();
      //  if(isSuccess){
            Account account = new Account();
            account.setName(accountName);
            account.setLevel(random.nextInt());
            callback.onSuccess(account);
      //  }else {
       //     callback.onFailed();
       // }
    }
}