package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.AppLaunchInteractor
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import com.nimtego.plectrum.presentation.mvp.view.ProgressView
import com.nimtego.plectrum.presentation.navigation.Screens
import io.reactivex.CompletableObserver
import io.reactivex.observers.DisposableCompletableObserver
import ru.terrakok.cicerone.Router
import rx.exceptions.OnErrorNotImplementedException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
        private val appRouter: Router,
        private val interactor: AppLaunchInteractor,
        private val schedulersProvider: SchedulersProvider
) : BasePresenter<ProgressView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.interactor.coldStart()
                .timeout(2, TimeUnit.SECONDS)
                .observeOn(schedulersProvider.ui())
                .doOnSubscribe { this@SplashPresenter.viewState.showProgress(true) }
                .doAfterTerminate { this@SplashPresenter.viewState.showProgress(false) }
                .subscribe (
                        {
                            this@SplashPresenter.navigateToStartScreen()
                        },
                        {
                            this@SplashPresenter.navigateToStartScreen()
                        }
                ).connect()
    }

    private fun navigateToStartScreen() {
        this.appRouter.replaceScreen(Screens.BottomNavigationScreen)
    }

    companion object {
        private val TAG = SplashPresenter::class.java.simpleName
    }

}