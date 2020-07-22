package com.lg.dome.mvvms.ui


import com.lg.baselib.base.BaseFragment

import com.lg.dome.R
import com.lg.dome.databinding.FragmentMeBinding

/**
 */
class MeFragment : BaseFragment<FragmentMeBinding>(){
    override fun initViewModel() {

    }

    override fun layoutId()= R.layout.fragment_me
}

//class MeFragment : Fragment(){
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding: FragmentMeBinding = FragmentMeBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//}