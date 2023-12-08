package com.example.mod_main.ui.category.adapter

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.example.lib_common.model.CategoryItem
import com.example.lib_framework.adapter.BaseBindViewHolder
import com.example.lib_framework.adapter.BaseRecyclerViewAdapter
import com.example.lib_framework.ext.gone
import com.example.lib_framework.ext.visible
import com.example.lib_framework.utils.dpToPx
import com.example.lib_framework.utils.getColorFromResource
import com.example.mod_main.R
import com.example.mod_main.databinding.LayoutCategoryTabItemBinding

class CategoryTabAdapter : BaseRecyclerViewAdapter<CategoryItem, LayoutCategoryTabItemBinding>() {
    private val mRadius = dpToPx(8f)
    private var currPosition = 0
    fun setCurrentPosition(position: Int) {
        this.currPosition = position
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutCategoryTabItemBinding>,
        item: CategoryItem?,
        position: Int
    ) {
        if (item == null) return

        holder.binding.apply {
            tvTitle.text = item.name
            if (item.isSelected == true) {
                viewTag.visible()
                tvTitle.background = getBgDrawable(R.color.white)
                tvTitle.typeface = Typeface.DEFAULT_BOLD
            } else {
                when (position) {
                    //前一个Item背景
                    currPosition - 1 -> {
                        tvTitle.background =
                            getBgDrawable(
                                color = R.color.color_f0f2f4,
                                rightBottomRadius = mRadius
                            )
                    }
                    //后一个Item背景
                    currPosition + 1 -> {
                        tvTitle.background =
                            getBgDrawable(
                                color = R.color.color_f0f2f4,
                                rightTopRadius = mRadius
                            )
                    }

                    else -> {
                        tvTitle.background =
                            getBgDrawable(color = R.color.color_f0f2f4)
                    }
                }
                viewTag.gone()
                tvTitle.typeface = Typeface.DEFAULT
            }
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutCategoryTabItemBinding {
        return LayoutCategoryTabItemBinding.inflate(layoutInflater, parent, false)
    }

    private fun getBgDrawable(
        @ColorRes color: Int,
        leftTopRadius: Float = 0.0f,
        rightTopRadius: Float = 0.0f,
        rightBottomRadius: Float = 0.0f,
        leftBottomRadius: Float = 0.0f
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(getColorFromResource(color))
            shape = GradientDrawable.RECTANGLE
            cornerRadii = floatArrayOf(
                leftTopRadius,
                leftTopRadius,
                rightTopRadius,
                rightTopRadius,
                rightBottomRadius,
                rightBottomRadius,
                leftBottomRadius,
                leftBottomRadius
            )
        }
    }
}