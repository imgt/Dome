package com.lg.baselib.base

import android.content.pm.ApplicationInfo
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lg.baselib.base.model.SharedViewModel


/**
 * Create by KunMinX at 19/8/1
 */
abstract class BaseActivity< DB : ViewDataBinding>: AppCompatActivity() {
    lateinit var mBinding: DB
    var sharedViewModel: SharedViewModel? = null
    private var mActivityProvider: ViewModelProvider? = null
    protected abstract fun initViewModel()

    abstract fun layoutId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = (applicationContext as BaseApp).getAppViewModelProvider(this).get(SharedViewModel::class.java)
        initViewModel()
        createViewDataBinding()

    }

    /**
     * 创建DataBinding
     */
    private fun createViewDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.lifecycleOwner = this
    }

    val isDebug: Boolean get() = applicationContext.applicationInfo != null &&
            applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

    protected fun showLongToast(text: String?) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    protected fun showShortToast(text: String?) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToast(stringRes: Int) {
        showLongToast(applicationContext.getString(stringRes))
    }

    protected fun showShortToast(stringRes: Int) {
        showShortToast(applicationContext.getString(stringRes))
    }

    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider!![modelClass]
    }

}