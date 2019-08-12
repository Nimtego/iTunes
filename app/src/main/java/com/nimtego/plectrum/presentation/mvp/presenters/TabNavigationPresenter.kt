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
import javax.inject.Qualifier

@InjectViewState
class TabNavigationPresenter @Inject constructor(
        private val router: Router,
        private val userSearchItemStorage: UserSearchItemStorage
) : BaseNavigationPresenter<TabNavigationView>() {

    private var currentSearchSubscriber: CurrentSearchSubscriber
    private lateinit var navigationQualifier: String
    private var isSearchState: Boolean = false

    init {
        this.currentSearchSubscriber = CurrentSearchSubscriber()
    }

    override fun onBackPressed(): Boolean {
        this.viewState.search(false)
        this.isSearchState = false
        this.router.exit()
        return true
    }

    override fun attachView(view: TabNavigationView) {
        super.attachView(view)
        this.userSearchItemStorage.getCurrentSearchTextObservable()
                .subscribe(CurrentSearchSubscriber())
        viewIsVisible(true)
    }

    override fun detachView(view: TabNavigationView) {
        super.detachView(view)
        this.currentSearchSubscriber.unsubscribe()
        viewIsVisible(false)
    }

    fun viewIsVisible(visible: Boolean) {
        if (this.isSearchState) {
            this.viewState.search(visible)
        }
    }

    private fun navigateToSearch() {
        this.isSearchState = true
        this.viewState.search(this.isSearchState)
        this.router.navigateTo(Screens.SearchScreen(this.navigationQualifier))
    }

    fun setNavigationQualifiers(tabNavigationQualifier:  String) {
        this.navigationQualifier = tabNavigationQualifier
    }

    private inner class CurrentSearchSubscriber : Subscriber<String>() {
        override fun onCompleted() {}
        override fun onNext(result: String) {
            this@TabNavigationPresenter.navigateToSearch()
        }
        override fun onError(e: Throwable) {}
    }

}