package com.example.mod_main.ui.system.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lib_framework.adapter.BaseBindViewHolder
import com.example.lib_framework.adapter.BaseRecyclerViewAdapter
import com.example.mod_main.databinding.LayoutSystemSecondItemBinding
import com.sum.common.model.SystemSecondList


/**
 * @author mingyan.su
 * @date   2023/3/21 8:49
 * @desc   体系adapter
 */
class SystemSecondAdapter : BaseRecyclerViewAdapter<SystemSecondList, LayoutSystemSecondItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutSystemSecondItemBinding {
        return LayoutSystemSecondItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutSystemSecondItemBinding>,
        item: SystemSecondList?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            tvName.text = item.name
        }
    }

}