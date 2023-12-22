package com.example.mod_video.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lib_framework.adapter.BaseBindViewHolder
import com.example.lib_framework.adapter.BaseRecyclerViewAdapter
import com.example.lib_framework.ext.onClick
import com.example.lib_framework.toast.TipsToast
import com.example.mod_video.R
import com.example.mod_video.databinding.LayoutVideoItemBinding
import com.xxmm.lib_room.entity.VideoInfo

/**
 * @author mingyan.su
 * @date   2023/4/3 12:41
 * @desc   视频Adapter
 */
class VideoAdapter : BaseRecyclerViewAdapter<VideoInfo, LayoutVideoItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutVideoItemBinding {
        return LayoutVideoItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutVideoItemBinding>,
        item: VideoInfo?,
        position: Int
    ) {
        item?.let {
            holder.binding.tvAuthor.text = "@${it.authorName}"
            holder.binding.tvTitle.text = it.title + it.desc
        }

        holder.binding.rotateNoteView.initAnimator()
        holder.binding.includeVideoAction.tvLike.text = "10"
        holder.binding.includeVideoAction.tvComment.text = "24"
        holder.binding.includeVideoAction.tvShare.text = "0"

        holder.binding.includeVideoAction.tvCommentOpen.onClick { showToast() }
        holder.binding.includeVideoAction.tvLike.onClick { showToast() }
        holder.binding.includeVideoAction.tvComment.onClick { showToast() }
        holder.binding.includeVideoAction.tvShare.onClick { showToast() }
    }

    private fun showToast() {
        TipsToast.showTips(R.string.default_developing)
    }
}