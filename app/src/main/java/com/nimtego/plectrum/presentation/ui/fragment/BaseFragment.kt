package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.nimtego.plectrum.presentation.ui.activity.AppActivity
import com.nimtego.plectrum.presentation.utils.StatusBarUtils

abstract class BaseFragment :  MvpAppCompatFragment() {

    open fun setupStatusBar() {
        StatusBarUtils.makeIconsDark(this.context, this.activity?.window)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBar()
    }

    open fun showStatusBar(): Boolean = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (this.activity as AppActivity).showStatusBar(showStatusBar())
    }

    override fun onDestroy() {
        if (this.activity?.isFinishing == true) {
            (this.activity as AppActivity).showStatusBar(true)
        }
        super.onDestroy()
    }
}