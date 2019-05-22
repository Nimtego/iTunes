package com.nimtego.plectrum.presentation.migrate_kotlin.presenters

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.nimtego.plectrum.presentation.migrate_kotlin.Screens
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.BaseView
import ru.terrakok.cicerone.Router

abstract class BasePresenter<T : BaseView>(private val router: Router?,
                                           private val screenNumber: Int)
    : MvpPresenter<T>() {

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

    fun search(request: String) {

    }

//Mark: navigation

    fun onBackCommandClick() {
        router?.exit()
    }

    fun onForwardCommandClick() {
        router?.navigateTo(Screens.SampleScreen(1))
    }

    fun onReplaceCommandClick() {
        router?.replaceScreen(Screens.SampleScreen(1))
    }

    fun onNewChainCommandClick() {
        router?.newChain(
                Screens.SampleScreen(screenNumber + 1),
                Screens.SampleScreen(screenNumber + 2),
                Screens.SampleScreen(screenNumber + 3)
        )
    }

    fun onFinishChainCommandClick() {
        router?.finishChain()
    }

    fun onNewRootCommandClick() {
        router?.newRootScreen(Screens.SampleScreen(screenNumber + 1))
    }

    fun onForwardWithDelayCommandClick() {
//        if (future != null) future.cancel(true)
//        future = executorService.schedule(Runnable {
//            //WARNING! Navigation must be only in UI thread.
//            Handler(Looper.getMainLooper()).post(
//                    Runnable { router.navigateTo(Screens.SampleScreen(screenNumber + 1)) }
//            )
//        }, 5, TimeUnit.SECONDS)
    }

    fun onBackToCommandClick() {
        router?.backTo(Screens.SampleScreen(3))
    }
}