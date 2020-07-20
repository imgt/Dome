package com.lg.dome.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lg.dome.R;
import com.lg.dome.mvp.model.bean.User;
import com.lg.dome.mvp.presenter.LoginPresenter;
import com.lg.dome.mvp.presenter.impl.LoginPresenterImpl;
import com.lg.dome.mvp.view.LoginView;

/**
 * Created by lqj on 2020/7/20.
 */
public class MvpActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private TextView tv;
    private LoginPresenter presenter;
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
        presenter = new LoginPresenterImpl(this);
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
