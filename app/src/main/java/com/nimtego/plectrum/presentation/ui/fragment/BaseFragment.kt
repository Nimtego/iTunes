package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.nimtego.plectrum.presentation.utils.StatusBarUtils

abstract class BaseFragment :  MvpAppCompatFragment() {

    open fun setupStatusBar() {
        StatusBarUtils.makeIconsDark(this.context, this.activity?.window)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBar()
    }
}