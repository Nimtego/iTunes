package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.mvp.view.ProgressView
import com.nimtego.plectrum.presentation.navigation.Screens
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
        private val appRouter: Router,
        private val interactor: LaunchUseCase,
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