package com.nimtego.plectrum.presentation.utils

import android.support.design.widget.TabLayout

@FunctionalInterface
interface TabSelectedListener : TabLayout.OnTabSelectedListener {

    override fun onTabUnselected(tab: TabLayout.Tab) {} //stub
}
