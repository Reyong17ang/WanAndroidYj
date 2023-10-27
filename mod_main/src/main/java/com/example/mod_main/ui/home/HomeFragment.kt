package com.example.mod_main.ui.home

import android.os.Bundle
import android.view.View
import com.example.lib_framework.base.BaseMvvmFragment
import com.example.mod_main.databinding.FragmentHomeBinding
import com.example.mod_main.ui.home.viewmodel.HomeViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>(),OnRefreshListener {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.refreshLayout?.apply {
            autoRefresh()
            setEnableAutoLoadMore(true)
            setEnableRefresh(true)
            setOnRefreshListener(this@HomeFragment)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refresh()
    }

    private fun refresh() {

    }
}