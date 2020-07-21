
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
 *
 */
abstract class BaseActivity : AppCompatActivity() {
    var sharedViewModel: SharedViewModel? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mBinding: ViewDataBinding? = null
    protected abstract fun initViewModel()
    protected abstract val dataBindingConfig: DataBindingConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = (applicationContext as BaseApp).getAppViewModelProvider(this).get(SharedViewModel::class.java)
        initViewModel()
        val dataBindingConfig = dataBindingConfig
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, dataBindingConfig.layout)
        binding.lifecycleOwner = this
        // binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
        val bindingParams = dataBindingConfig.bindingParams
        var i = 0
        val length = bindingParams.size()
        while (i < length) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            i++
        }
        mBinding = binding
    }

    val isDebug: Boolean
        get() = applicationContext.applicationInfo != null &&
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