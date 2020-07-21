
package com.lg.baselib.base

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.lg.baselib.base.model.SharedViewModel
import com.lg.baselib.base.nav.NavHostFragment

/**
 */
abstract class BaseFragment : Fragment() {
    protected var mActivity: AppCompatActivity? = null
    var sharedViewModel: SharedViewModel? = null
    protected var mAnimationLoaded = false
    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mBinding: ViewDataBinding? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    protected abstract fun initViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel =
            (mActivity!!.applicationContext as BaseApp).getAppViewModelProvider(
                mActivity
            ).get(
                SharedViewModel::class.java
            )
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected abstract val dataBindingConfig: DataBindingConfig
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBindingConfig = dataBindingConfig
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            dataBindingConfig.layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        //  binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
        val bindingParams = dataBindingConfig.bindingParams
        var i = 0
        val length = bindingParams.size()
        while (i < length) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            i++
        }
        mBinding = binding
        return binding.root
    }

    override fun onCreateAnimation(
        transit: Int,
        enter: Boolean,
        nextAnim: Int
    ): Animation? {
        HANDLER.postDelayed({
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }, 280)
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    protected fun loadInitData() {}
    val isDebug: Boolean
        get() = mActivity!!.applicationContext.applicationInfo != null &&
                mActivity!!.applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

    protected fun showLongToast(text: String?) {
        Toast.makeText(mActivity!!.applicationContext, text, Toast.LENGTH_LONG).show()
    }

    protected fun showShortToast(text: String?) {
        Toast.makeText(mActivity!!.applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToast(stringRes: Int) {
        showLongToast(mActivity!!.applicationContext.getString(stringRes))
    }

    protected fun showShortToast(stringRes: Int) {
        showShortToast(mActivity!!.applicationContext.getString(stringRes))
    }

    protected fun <T : ViewModel?> getFragmentViewModel(modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider!![modelClass]
    }

    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(mActivity!!)
        }
        return mActivityProvider!![modelClass]
    }

    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    companion object {
        private val HANDLER = Handler()
    }
}