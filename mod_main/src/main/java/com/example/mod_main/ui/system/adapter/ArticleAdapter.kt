package com.example.mod_main.ui.system.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lib_framework.adapter.BaseBindViewHolder
import com.example.lib_framework.adapter.BaseRecyclerViewAdapter
import com.example.lib_framework.ext.Bold
import com.example.lib_framework.ext.gone
import com.example.lib_framework.ext.onClick
import com.example.lib_framework.ext.visible
import com.example.lib_framework.utils.getStringFromResource
import com.example.mod_main.R
import com.example.mod_main.databinding.LayoutArticleItemBinding
import com.sum.common.model.ArticleInfo

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author mingyan.su
 * @date   2023/3/21 22:50
 * @desc   文章列表Item
 */
class ArticleAdapter : BaseRecyclerViewAdapter<ArticleInfo, LayoutArticleItemBinding>() {
    var onItemCollectListener: ((view: View, position: Int) -> Unit?)? = null
    private val format = SimpleDateFormat("yyyy-MM-dd:HH:mm", Locale.CHINA)

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutArticleItemBinding {
        return LayoutArticleItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutArticleItemBinding>,
        item: ArticleInfo?,
        position: Int
    ) {
        if (item == null) return
        val name = if (item.author.isNullOrEmpty()) item.shareUser else item.author
        val authorName = String.format(getStringFromResource(R.string.author_name), name)
        holder.binding.apply {
            tvTitle.text = item.title
            tvTitle.Bold()
            tvDesc.text = item.desc
            if (item.desc.isNullOrEmpty()) {
                tvDesc.gone()
            } else {
                tvDesc.visible()
            }
            tvTime.text = format.format(item.publishTime)
            tvFrom.text = "${item.superChapterName}/${item.chapterName}"
            tvAuthorName.text = authorName
            tvZan.text = "${item.zan ?: "0"}"
            ivCollect.onClick {
                onItemCollectListener?.invoke(it, position)
            }
            ivCollect.isSelected = item.collect ?: false
        }
    }


}