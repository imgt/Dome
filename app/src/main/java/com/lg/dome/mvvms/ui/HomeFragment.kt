package com.lg.dome.mvvms.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lg.baselib.base.BaseFragment
import com.lg.dome.R
import com.lg.dome.databinding.FragmentHomeBinding

/**
 * Created by lqj on 2020/7/22.
 */
class HomeFragment   : BaseFragment<FragmentHomeBinding>(){
    override fun initViewModel() {

    }

    override fun layoutId()= R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragments = getFragments()

        mBinding.vp.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }

            override fun getItemCount(): Int {
                return fragments.size
            }
        }
        //禁用左右滑动切换页签
        mBinding.vp.isUserInputEnabled = true

        mBinding.bnvView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.pageFragment -> {
                    mBinding.vp.setCurrentItem(0, false)
                }
                R.id.otherFragment -> {
                    mBinding.vp.setCurrentItem(1, false)
                }
                R.id.meFragment -> {
                    mBinding.vp.setCurrentItem(2, false)
                }
            }
            true
        }
    }
    private fun getFragments(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>(3)

        val favoritesFragment = PageFragment()
        var bundle = Bundle()
//        bundle.putString("title", getString(R.string.favorites))
        favoritesFragment.arguments = bundle

        val schedulesFragment = OtherFragment()
        bundle = Bundle()
        //  bundle.putString("title", getString(R.string.schedules))
        schedulesFragment.arguments = bundle

        val mineFragment = MeFragment()
        bundle = Bundle()
        // bundle.putString("title", getString(R.string.mine))
        mineFragment.arguments = bundle

        fragments.add(favoritesFragment)
        fragments.add(schedulesFragment)
        fragments.add(mineFragment)
        return fragments
    }
}