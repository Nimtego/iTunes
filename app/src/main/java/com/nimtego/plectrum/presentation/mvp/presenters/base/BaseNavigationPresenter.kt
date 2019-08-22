package com.nimtego.plectrum.presentation.mvp.presenters.base

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import com.nimtego.plectrum.presentation.utils.BackButtonListener

abstract class BaseNavigationPresenter<T : BaseView> : MvpPresenter<T>(), BackButtonListener {

    protected val isViewAttached: Boolean get() = attachedViews.size > 0

    private val tag = javaClass.simpleName

    override fun attachView(view: T) {
        Log.i(this.tag, "attachView")
        super.attachView(view)
    }

    override fun detachView(view: T) {
        Log.i(this.tag, "detachView")
        super.detachView(view)
    }

}