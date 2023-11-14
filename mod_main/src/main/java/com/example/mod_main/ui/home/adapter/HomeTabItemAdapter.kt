package com.example.mod_main.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lib_framework.adapter.BaseBindViewHolder
import com.example.lib_framework.adapter.BaseRecyclerViewAdapter
import com.example.lib_framework.ext.onClick
import com.example.lib_framework.utils.ViewUtils
import com.example.lib_framework.utils.dpToPx
import com.example.mod_main.databinding.LayoutHomeTabItemBinding
import com.example.mod_main.ui.ImagePreviewActivity
import com.sum.common.model.ProjectSubInfo
import com.sum.glide.setUrl

class HomeTabItemAdapter :BaseRecyclerViewAdapter<ProjectSubInfo,LayoutHomeTabItemBinding>() {
    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutHomeTabItemBinding>,
        item: ProjectSubInfo?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            ivMainIcon.setUrl(item.envelopePic)
            tvTitle.text = item.title
            tvSubTitle.text = item.desc
            tvAuthorName.text = item.author
            tvTime.text = item.niceDate
            ivMainIcon.onClick {
                ImagePreviewActivity.start(it.context, item.envelopePic)
            }
            ViewUtils.setClipViewCornerRadius(holder.itemView, dpToPx(8))
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutHomeTabItemBinding {
        return LayoutHomeTabItemBinding.inflate(layoutInflater,parent,false)
    }
}