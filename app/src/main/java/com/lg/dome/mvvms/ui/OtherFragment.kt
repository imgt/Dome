package com.lg.dome.mvvms.ui


import com.lg.baselib.base.BaseFragment
import com.lg.dome.R
import com.lg.dome.databinding.FragmentOtherBinding


/**
 */
class OtherFragment : BaseFragment<FragmentOtherBinding>() {
    override fun initViewModel() {

    }

    override fun layoutId() = R.layout.fragment_other
}
//class OtherFragment :Fragment(){
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding: FragmentOtherBinding = FragmentOtherBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//}