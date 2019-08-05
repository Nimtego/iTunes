package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import com.nimtego.plectrum.presentation.navigation.Screens
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import rx.Subscriber
import javax.inject.Inject

@InjectViewState
class TabNavigationPresenter @Inject constructor(
        private val router: Router,
        private val userSearchItemStorage: UserSearchItemStorage
) : BaseNavigationPresenter<TabNavigationView>() {

    private var currentSearchSubscriber: CurrentSearchSubscriber

    init {
        this.currentSearchSubscriber = CurrentSearchSubscriber()
    }

    override fun onBackPressed(): Boolean {
        this.router.exit()
        return true
    }

    override fun attachView(view: TabNavigationView) {
        super.attachView(view)
        this.userSearchItemStorage.getCurrentSearchTextObservable()
                .subscribe(this.currentSearchSubscriber)
    }

    override fun detachView(view: TabNavigationView) {
        super.detachView(view)
        this.currentSearchSubscriber.unsubscribe()
    }

    fun viewIsVisible(visible: Boolean) {
        if (visible) {
            if (this.currentSearchSubscriber.isUnsubscribed) {
                this.currentSearchSubscriber = CurrentSearchSubscriber()
            }
            this.userSearchItemStorage.getCurrentSearchTextObservable()
                    .subscribe(this.currentSearchSubscriber)
        } else {
            this.currentSearchSubscriber.unsubscribe()
        }
    }

    private fun navigateToSearch() {
        //todo search screen to navigate
        this.router.navigateTo(Screens.SearchScreen(NavigationQualifiers.TAB_MUSIC_NAVIGATION))
    }

    private inner class CurrentSearchSubscriber : Subscriber<String>() {
        override fun onCompleted() {}
        override fun onNext(result: String) {
            this@TabNavigationPresenter.navigateToSearch()
        }
        override fun onError(e: Throwable) {}
    }

}