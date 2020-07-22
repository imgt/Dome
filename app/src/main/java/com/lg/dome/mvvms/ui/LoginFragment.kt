package com.lg.dome.mvvms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lg.dome.R
import com.lg.dome.databinding.FragmentLoginBinding

/**
 */
class LoginFragment : Fragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data Binding
        val binding: FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        onSubscribeUi(binding)
        return binding.root
    }

    /**
     * Binding绑定
     */
    private fun onSubscribeUi(binding: FragmentLoginBinding) {
        binding.activity=activity
    }
}