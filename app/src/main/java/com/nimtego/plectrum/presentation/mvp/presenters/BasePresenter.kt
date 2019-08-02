package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.nimtego.plectrum.domain.interactor.BaseInteractor
import com.nimtego.plectrum.presentation.interactor.DisposableInteractor
import com.nimtego.plectrum.presentation.interactor.Interactor
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

abstract class BasePresenter<T : BaseView>(
        //private val disposableInteractor: DisposableInteractor
) : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

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

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

}