package com.example.mod_main.ui.system

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_framework.base.BaseMvvmFragment
import com.example.lib_framework.ext.toJson
import com.example.lib_framework.ext.visible
import com.example.lib_framework.utils.dpToPx
import com.example.mod_main.databinding.FragmentSystemBinding
import com.example.mod_main.ui.system.adapter.SystemAdapter
import com.example.mod_main.ui.system.viewmodel.SystemViewModel
import com.sum.framework.decoration.NormalItemDecoration

class SystemFragment : BaseMvvmFragment<FragmentSystemBinding, SystemViewModel>() {
    private lateinit var mAdapter: SystemAdapter
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mAdapter = SystemAdapter()
        mBinding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(NormalItemDecoration().apply {
                setBounds(left = dpToPx(8), top = dpToPx(10), right = dpToPx(8), bottom = dpToPx(10))
                setLastBottom(true)
            })
        }
        mAdapter.onItemClickListener = { view: View, position: Int ->
            val item = mAdapter.getItem(position)
            ArticleTabActivity.startIntent(requireContext(), item?.toJson(true))
        }
    }
    override fun initData() {
        showLoading()
        mViewModel.getSystemList().observe(this) {
            mAdapter.setData(it)
            dismissLoading()
        }
        mViewModel.errorListLiveData.observe(this) {
            //空数据视图
            mBinding?.viewEmptyData?.visible()
        }
    }
}