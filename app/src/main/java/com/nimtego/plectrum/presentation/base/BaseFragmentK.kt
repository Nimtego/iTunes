package com.nimtego.plectrum.presentation.base

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatFragment
import com.nimtego.plectrum.presentation.mvp.BaseView
import com.nimtego.plectrum.presentation.mvp.ProgressView

abstract class BaseFragmentK : MvpAppCompatFragment(), ProgressView {

    protected var parent: ProgressView? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ProgressView)
            this.parent = context
    }

    override fun showProgress() {
        parent?.showProgress()
    }

    override fun hideProgress() {
        parent?.hideProgress()
    }

}