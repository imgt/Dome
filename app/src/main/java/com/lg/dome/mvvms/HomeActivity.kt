package com.lg.dome.mvvms


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lg.baselib.base.BaseActivity
import com.lg.dome.R
import com.lg.dome.databinding.ActivityHomeBinding
import com.lg.dome.mvvms.ui.MeFragment
import com.lg.dome.mvvms.ui.OtherFragment
import com.lg.dome.mvvms.ui.PageFragment


/**
 */
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun layoutId() = R.layout.activity_home
    override fun initViewModel() {

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val  navController = Navigation.findNavController(this,R.id.nav_host_fragment);
//      //  NavigationUI.setupActionBarWithNavController(this, navController)
//        NavigationUI.setupWithNavController(mBinding.bnvView,navController)
//        mBinding.bnvView.itemIconTintList = null
//        initBottomNavigationView(mBinding.bnvView,navController)
//    }
//
//    /**
//     * Navigation绑定bottomNavigationView
//     */
//    private fun initBottomNavigationView(bottomNavigationView: BottomNavigationView, navController: NavController) {
//       // bottomNavigationView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            when(destination.id){
//                R.id.meFragment ->{
//
//                }
//            }
//        }
//        bottomNavigationView.setOnNavigationItemSelectedListener {item->
//            val itemId: Int = item.itemId
//            when (itemId) {
//                R.id.pageFragment -> {
//                    //当该项已经被选中时，不执行任何操作
//                    if (item.isChecked) {
//                        return@setOnNavigationItemSelectedListener true
//                    }
//                    navController.navigate(R.id.pageFragment)
//                }
//                R.id.otherFragment -> {
//                    //当该项已经被选中时，不执行任何操作
//                    if (item.isChecked) {
//                        return@setOnNavigationItemSelectedListener true
//                    }
//                    navController.navigate(R.id.otherFragment)
//                }
//                R.id.meFragment -> {
//                    if (item.isChecked) {
//                        return@setOnNavigationItemSelectedListener true
//                    }
//                    navController.navigate(R.id.meFragment)
//                }
//            }
//            return@setOnNavigationItemSelectedListener false
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}


//class HomeActivity : AppCompatActivity() {
//
//    lateinit var bottomNavigationView: BottomNavigationView
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//        bottomNavigationView = findViewById(R.id.bnv_view)
//        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = host.navController
//        initBottomNavigationView(bottomNavigationView,navController)
//    }
//
//    /**
//     * Navigation绑定bottomNavigationView
//     */
//    private fun initBottomNavigationView(bottomNavigationView: BottomNavigationView, navController: NavController) {
//        bottomNavigationView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            when(destination.id){
//            }
//        }
//    }
//
//}