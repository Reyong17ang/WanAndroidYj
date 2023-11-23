package com.example.lib_banner.annotation

import IndicatorSlideMode.Companion.COLOR
import IndicatorSlideMode.Companion.NORMAL
import IndicatorSlideMode.Companion.SCALE
import IndicatorSlideMode.Companion.SMOOTH
import IndicatorSlideMode.Companion.WORM
import androidx.annotation.IntDef


/**
 * 指示器滑动模式
 */
@IntDef(NORMAL, SMOOTH, WORM, COLOR, SCALE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AIndicatorSlideMode
