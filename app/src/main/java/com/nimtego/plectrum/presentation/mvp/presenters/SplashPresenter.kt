package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.AppLaunchInteractor
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import com.nimtego.plectrum.presentation.navigation.Screens
import io.reactivex.observers.DisposableCompletableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
        private val appRouter: Router,
        private val interactor: AppLaunchInteractor
) : BasePresenter<BaseView>(interactor) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.interactor.coldStart(object : DisposableCompletableObserver() {

            override fun onComplete() { this@SplashPresenter.navigateToStartScreen() }

            override fun onError(e: Throwable) {
                this@SplashPresenter.viewState.systemMessage("$e")
                Log.i(TAG, "on error $e")
            }
        }, params = AppLaunchInteractor.Params.forRequest("First start"))
    }

    private fun navigateToStartScreen() {
        this.appRouter.replaceScreen(Screens.BottomNavigationScreen)
    }

    companion object {
        private val TAG = SplashPresenter::class.java.simpleName
    }

}