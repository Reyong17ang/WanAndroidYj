package com.example.mod_main.ui.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lib_common.constant.KEY_DATA
import com.example.lib_framework.adapter.ViewPage2FragmentAdapter
import com.example.lib_framework.base.BaseDataBindActivity
import com.example.lib_framework.ext.onClick
import com.example.lib_framework.ext.toBeanOrNull
import com.example.lib_framework.log.LogUtil
import com.example.lib_framework.toast.TipsToast
import com.example.mod_main.databinding.ActivityArticleBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sum.common.model.SystemList
import com.sum.common.model.SystemSecondList

class ArticleTabActivity:BaseDataBindActivity<ActivityArticleBinding>() {
    private val mArrayTabFragments = SparseArray<Fragment>()

    private var mTabLayoutMediator: TabLayoutMediator? = null
    private var mFragmentAdapter: ViewPage2FragmentAdapter? = null
    private var systemSecondList: MutableList<SystemSecondList>? = null
    companion object{
       fun startIntent(context: Context,tabListJson: String?){
           val intent = Intent(context, ArticleTabActivity::class.java)
           tabListJson?.let {
               intent.putExtra(KEY_DATA, tabListJson)
           }
           context.startActivity(intent)
       }
    }
    override fun initView(savedInstanceState: Bundle?) {
        initTab()
        mBinding.ivSearch.onClick {
            TipsToast.showTips("完善中。。。")
//            SearchServiceProvider.toSearch(this)
        }
    }

    override fun initData() {
        val dataJson = intent?.getStringExtra(KEY_DATA)
        val data = dataJson?.toBeanOrNull<SystemList>()

        mBinding.titleBar.setMiddleText(data?.name)
        systemSecondList = data?.children
        systemSecondList?.forEachIndexed { index, item ->
            mArrayTabFragments.append(index, ArticleListFragment.newInstance(item.id ?: 0))
        }
        mFragmentAdapter?.setData(mArrayTabFragments)
        mFragmentAdapter?.notifyDataSetChanged()
    }

    private fun initTab() {
        mFragmentAdapter = ViewPage2FragmentAdapter(supportFragmentManager, lifecycle, mArrayTabFragments)
        mBinding.let {
            it.viewPager.adapter = mFragmentAdapter
            //可左右滑动
            it.viewPager.isUserInputEnabled = true
            //禁用预加载
            it.viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

            mTabLayoutMediator = TabLayoutMediator(it.tabHome, it.viewPager) { tab: TabLayout.Tab, position: Int ->
                if (!systemSecondList.isNullOrEmpty() && position < systemSecondList!!.size) {
                    tab.text = systemSecondList!![position].name
                }
            }
            //tabLayout和viewPager2关联起来
            mTabLayoutMediator?.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mTabLayoutMediator?.detach()
    }

}