package com.lg.dome.mvvms

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lg.baselib.base.BaseActivity
import com.lg.dome.R
import com.lg.dome.databinding.ActivityHomeBinding

/**
 */
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun layoutId() = R.layout.activity_home
    override fun initViewModel() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController
        initBottomNavigationView(mBinding.bnvView,navController)
    }

    /**
     * Navigation绑定bottomNavigationView
     */
    private fun initBottomNavigationView(bottomNavigationView: BottomNavigationView, navController: NavController) {
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
            }
        }
    }

}
