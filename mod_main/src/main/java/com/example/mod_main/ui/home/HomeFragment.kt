package com.example.mod_main.ui.home

import android.graphics.Typeface
import android.os.Bundle
import android.util.SparseArray
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lib_framework.adapter.ViewPage2FragmentAdapter
import com.example.lib_framework.base.BaseMvvmFragment
import com.example.lib_framework.ext.gone
import com.example.lib_framework.ext.visible
import com.example.lib_framework.log.LogUtil
import com.example.mod_main.R
import com.example.mod_main.databinding.FragmentHomeBinding
import com.example.mod_main.ui.home.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.sum.common.model.ProjectTabItem

class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>(), OnRefreshListener {
    private var mTabLayoutMediator: TabLayoutMediator? = null
    private var mArrayTabFragments = SparseArray<Fragment>()
    private var mFragmentAdapter: ViewPage2FragmentAdapter? = null
    private var mProjectTabs: MutableList<ProjectTabItem> = mutableListOf()
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.refreshLayout?.apply {
            autoRefresh()
            setEnableRefresh(true)
            setEnableLoadMore(false)
            setOnRefreshListener(this@HomeFragment)
        }
        initTab()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refresh()
    }

    private fun refresh() {
        mViewModel.getBannerList().observe(this) { banners ->
            banners?.let {
                mBinding?.bannerHome?.visible()
                mBinding?.bannerHome?.setData(it)
            } ?: kotlin.run {
                mBinding?.bannerHome?.gone()
            }
            mBinding?.refreshLayout?.finishRefresh()
        }
        mViewModel.getProjectTab().observe(this) { tabs ->
            LogUtil.d(tabs.toString())
            tabs?.forEachIndexed { index, projectTabItem ->
                mProjectTabs.add(projectTabItem)
                mArrayTabFragments.append(index, HomeTabFragment.newInstance(tabs[index].id))
            }
            mFragmentAdapter?.setData(mArrayTabFragments)
            mFragmentAdapter?.notifyItemRangeChanged(0, mArrayTabFragments.size())
            // 解决 TabLayout 刷新数据后滚动到错误位置
            mBinding?.tabHome?.let {
                it.post { it.getTabAt(0)?.select() }
            }
            mBinding?.refreshLayout?.finishRefresh()
        }
    }

    private fun initTab() {
        activity.let {
            mFragmentAdapter =
                ViewPage2FragmentAdapter(childFragmentManager, lifecycle, mArrayTabFragments)
        }
        mBinding?.let {
            it.viewPager.adapter = mFragmentAdapter
            it.viewPager.isUserInputEnabled = true
            it.viewPager.offscreenPageLimit = 1
            mTabLayoutMediator =
                TabLayoutMediator(it.tabHome, it.viewPager) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mProjectTabs[position].name
                }
            mTabLayoutMediator?.attach()
            it.tabHome.addOnTabSelectedListener(tabSelectedCall)
            val tabFirst = it.tabHome.getTabAt(0)
            setTabTextSize(tabFirst)
        }
    }

    /**
     * tab选择回调
     */
    private val tabSelectedCall = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            setTabTextSize(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            //非选中效果在xml中设置
            tab?.customView = null
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    /**
     * 设置tab大小加粗效果
     */
    private fun setTabTextSize(tabFirst: TabLayout.Tab?) {
        TextView(requireContext()).apply {
            typeface = Typeface.DEFAULT_BOLD
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }.also {
            it.text = tabFirst?.text
            tabFirst?.customView = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mTabLayoutMediator?.detach()
    }
}