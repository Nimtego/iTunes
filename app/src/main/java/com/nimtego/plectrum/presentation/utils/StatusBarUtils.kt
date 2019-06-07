package com.nimtego.plectrum.presentation.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import com.nimtego.plectrum.R

object StatusBarUtils {

    fun makeIconsDark(context: Context?, window: Window?) {
        setIconsColor(context, window, View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    fun makeIconsLight(context: Context?, window: Window?) {
        setIconsColor(context, window, View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    private fun setIconsColor(context: Context?, window: Window?, flags: Int) {
        window?.decorView?.systemUiVisibility = flags
        context?.let {
            window?.statusBarColor = ContextCompat.getColor(it, R.color.status_bar_tint_color)
        }
    }
}