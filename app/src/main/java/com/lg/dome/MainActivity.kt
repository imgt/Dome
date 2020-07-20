package com.lg.dome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lg.dome.databinding.ActivityMvvmBinding
import com.lg.dome.mvvm.MVVMViewModel


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMvvmBinding? = null
    private var mvvmViewModel: MVVMViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        mvvmViewModel = MVVMViewModel(this, binding)
        binding!!.setViewModel(mvvmViewModel) //初始化viewModel
    }

}
