package com.nimtego.plectrum.presentation.utils

import android.support.design.widget.TabLayout

@FunctionalInterface
interface TabSelectedListener : TabLayout.OnTabSelectedListener {

    override fun onTabUnselected(tab: TabLayout.Tab) {} //stub

    override fun onTabReselected(tab: TabLayout.Tab) {} //stub
}
