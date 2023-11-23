package com.example.lib_banner.drawer
import CircleDrawer
import com.example.lib_banner.options.IndicatorOptions
import com.example.lib_banner.drawer.DashDrawer
import com.example.lib_banner.drawer.RoundRectDrawer


/**
 * Indicator Drawer Factory.
 */
internal object DrawerFactory {
    fun createDrawer(indicatorOptions: IndicatorOptions): IDrawer {
        return when (indicatorOptions.indicatorStyle) {
            IndicatorStyle.DASH -> DashDrawer(indicatorOptions)
            IndicatorStyle.ROUND_RECT -> RoundRectDrawer(indicatorOptions)
            else -> CircleDrawer(indicatorOptions)
        }
    }
}
