package com.nimtego.plectrum.presentation.mvp.presenters.base

import com.nimtego.plectrum.presentation.mvp.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

abstract class BaseContentPresenter<T : BaseView> : BasePresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    protected abstract var router: Router

    private val tag = javaClass.simpleName

    override fun attachView(view: T) {
        super.attachView(view)
//        if(!isInRestoreState(view)) {
            prepareViewModel()
//        }
    }

    protected abstract fun prepareViewModel()

    fun onBackPressed() {
        this.router.exit()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

}