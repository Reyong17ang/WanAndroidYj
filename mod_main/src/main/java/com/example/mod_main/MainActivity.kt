package com.example.mod_main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_common.constant.KEY_INDEX
import com.example.lib_common.constant.MAIN_ACTIVITY_HOME
import com.example.lib_framework.base.BaseDataBindActivity
import com.example.lib_framework.utils.StatusBarSettingHelper
import com.example.mod_main.databinding.ActivityMainBinding
import com.example.mod_main.navigator.SumFragmentNavigator

@Route(path = MAIN_ACTIVITY_HOME)
class MainActivity : BaseDataBindActivity<ActivityMainBinding>() {
    private lateinit var navController: NavController
    companion object {
        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        val navView = mBinding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        //2.自定义FragmentNavigator，mobile_navigation.xml文件中的fragment标识改为SumFragmentNavigator的sumFragment
        val fragmentNavigator =
            SumFragmentNavigator(this, navHostFragment.childFragmentManager, navHostFragment.id)
        //3.注册到Navigator里面，这样才找得到
        navController.navigatorProvider.addNavigator(fragmentNavigator)
        //4.设置Graph，需要将activity_main.xml文件中的app:navGraph="@navigation/mobile_navigation"移除
        navController.setGraph(R.navigation.mobile_navigation)
        navView.setupWithNavController(navController)
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        StatusBarSettingHelper.statusBarLightMode(this@MainActivity, true)
    }
}