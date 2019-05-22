package com.nimtego.plectrum.presentation.migrate_kotlin.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.DashboardView
import ru.terrakok.cicerone.Router
import com.nimtego.plectrum.presentation.migrate_kotlin.Screens


@InjectViewState
class DashboardPresenter(private val router: Router?, private val screenNumber: Int) : BasePresenter<DashboardView>() {

    fun search(request: String) {

    }

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