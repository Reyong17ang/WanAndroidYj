package com.example.lib_banner.annotation

import IndicatorSlideMode.Companion.NORMAL
import PageStyle.MULTI_PAGE
import PageStyle.MULTI_PAGE_OVERLAP
import PageStyle.MULTI_PAGE_SCALE
import androidx.annotation.IntDef

import java.lang.annotation.ElementType

/**
 * 指示器页面样式
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@IntDef(NORMAL, MULTI_PAGE, MULTI_PAGE_OVERLAP, MULTI_PAGE_SCALE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Target(ElementType.PARAMETER)
annotation class APageStyle()
