package com.nimtego.plectrum.presentation.utils;

import android.support.design.widget.TabLayout;

@FunctionalInterface
public interface TabSelectedListener extends TabLayout.OnTabSelectedListener {

    @Override
    default void onTabUnselected(TabLayout.Tab tab) {} //stub

    @Override
    default void onTabReselected(TabLayout.Tab tab) {} //stub
}
