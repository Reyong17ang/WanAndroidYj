package com.example.mod_main.ui.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lib_common.constant.KEY_LIST
import com.example.lib_common.model.CategorySecondItem
import com.example.lib_common.provider.MainServiceProvider
import com.example.lib_framework.base.BaseMvvmFragment
import com.example.lib_framework.ext.dividerGridSpace
import com.example.lib_framework.ext.gone
import com.example.lib_framework.ext.toBeanOrNull
import com.example.lib_framework.ext.visible
import com.example.mod_main.databinding.FragmentCategorySecondBinding
import com.example.mod_main.ui.category.adapter.CategorySecondItemAdapter
import com.example.mod_main.ui.category.viewmodel.CategoryViewModel

class CategorySecondFragment :
    BaseMvvmFragment<FragmentCategorySecondBinding, CategoryViewModel>() {
    private lateinit var mAdapter: CategorySecondItemAdapter

    companion object {
        fun newInstance(jsonStr: String): CategorySecondFragment {
            val fragment = CategorySecondFragment()
            val bundle = Bundle()
            bundle.putString(KEY_LIST, jsonStr)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mAdapter = CategorySecondItemAdapter()
        mBinding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
            dividerGridSpace(2, 8.0f, true)
        }
        mAdapter.onItemClickListener = { _: View, position: Int ->
            val item = mAdapter.getItem(position)
            if (item != null && !item.link.isNullOrEmpty()) {
                MainServiceProvider.toArticleDetail(
                    context = requireContext(),
                    url = item.link!!,
                    title = item.title ?: ""
                )
            }
        }
    }

    override fun initData() {
        val listJson = arguments?.getString(KEY_LIST, "")
        val list = listJson?.toBeanOrNull<MutableList<CategorySecondItem>>()
        mAdapter.setData(list)
        if (list.isNullOrEmpty()) {
            mBinding?.viewEmptyData?.visible()
        } else {
            mBinding?.viewEmptyData?.gone()
        }
    }
}