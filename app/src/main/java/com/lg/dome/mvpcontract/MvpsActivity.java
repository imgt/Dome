package com.lg.dome.mvpcontract;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lg.dome.R;

import com.lg.dome.mvp.model.bean.User;

import com.lg.dome.mvpcontract.Presenter.PresenterImpl;
import com.lg.dome.mvpcontract.contract.LoginContract;


/**
 * Created by lqj on 2020/7/20.
 */
public class MvpsActivity extends AppCompatActivity implements LoginContract.ILoginView, View.OnClickListener {
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private TextView tv;
    private LoginContract.ILoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        tv=findViewById(R.id.tv);
        findViewById(R.id.button).setOnClickListener(this);
        //创建一个presenter对象，当点击登录按钮时，让presenter去调用model层的login()方法，验证帐号密码
        presenter = new PresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError("用户名为空！");
    }

    @Override
    public void setPasswordError() {
        password.setError("密码为空！");
    }

    @Override
    public void showSuccess(String str) {
        progressBar.setVisibility(View.GONE);
        tv.setText(str);
        Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        User user = new User();
        user.setPassword(password.getText().toString());
        user.setUsername(username.getText().toString());
        presenter.validateCredentials(user);
    }
}
