package com.nimtego.plectrum.presentation.migrate_kotlin.presenters

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.BaseView

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {

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