package com.example.mod_main.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lib_common.model.CategorySecondItem
import com.example.lib_framework.adapter.BaseBindViewHolder
import com.example.lib_framework.adapter.BaseRecyclerViewAdapter
import com.example.lib_framework.utils.ViewUtils
import com.example.lib_framework.utils.dpToPx
import com.example.mod_main.databinding.LayoutCategorySecondItemBinding


/**
 * @author mingyan.su
 * @date   2023/3/19 22:45
 * @desc   分类二级Adapter
 */
class CategorySecondItemAdapter :
    BaseRecyclerViewAdapter<CategorySecondItem, LayoutCategorySecondItemBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutCategorySecondItemBinding {
        return LayoutCategorySecondItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutCategorySecondItemBinding>,
        item: CategorySecondItem?,
        position: Int
    ) {
        holder.binding?.apply {
            tvTitle.text = item?.title
            ViewUtils.setClipViewCornerRadius(tvTitle, dpToPx(8))
        }

    }
}