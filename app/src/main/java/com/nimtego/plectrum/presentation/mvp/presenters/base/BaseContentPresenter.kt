package com.nimtego.plectrum.presentation.mvp.presenters.base

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

abstract class BaseContentPresenter<T : BaseView> : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    protected abstract var router: Router?

    private val tag = javaClass.simpleName

    override fun attachView(view: T) {
        Log.i(this.tag, "attachView")
        super.attachView(view)
    }

    override fun detachView(view: T) {
        Log.i(this.tag, "detachView")
        super.detachView(view)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

}