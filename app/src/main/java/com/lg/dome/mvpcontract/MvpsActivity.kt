package com.lg.dome.mvpcontract

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lg.dome.R
import com.lg.dome.mvp.model.bean.User
import com.lg.dome.mvpcontract.Presenter.PresenterImpl
import com.lg.dome.mvpcontract.contract.LoginContract.ILoginPresenter
import com.lg.dome.mvpcontract.contract.LoginContract.ILoginView

/**
 * Created by lqj on 2020/7/20.
 */
class MvpsActivity : AppCompatActivity(), ILoginView, View.OnClickListener {
    private var progressBar: ProgressBar? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var tv: TextView? = null
    private var presenter: ILoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        progressBar = findViewById<View>(R.id.progress) as ProgressBar
        username = findViewById<View>(R.id.username) as EditText
        password = findViewById<View>(R.id.password) as EditText
        tv = findViewById(R.id.tv)
        findViewById<View>(R.id.button).setOnClickListener(this)
        //创建一个presenter对象，当点击登录按钮时，让presenter去调用model层的login()方法，验证帐号密码
        presenter = PresenterImpl(this)
    }

    override fun onDestroy() {
        presenter!!.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar!!.visibility = View.GONE
    }

    override fun setUsernameError() {
        username!!.error = "用户名为空！"
    }

    override fun setPasswordError() {
        password!!.error = "密码为空！"
    }

    override fun showSuccess(str: String?) {
        progressBar!!.visibility = View.GONE
        tv!!.text = str
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        val user = User()
        user.password = password!!.text.toString()
        user.username = username!!.text.toString()
        presenter!!.validateCredentials(user)
    }
}