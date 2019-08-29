package com.nimtego.plectrum.presentation.mvp.presenters.navigation

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.manger.TabsProvider
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.ui.auxiliary.TabContainer
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import rx.Subscriber
import javax.inject.Inject

@InjectViewState
class BottomNavigationPresenter @Inject constructor(
        private val bottomNavigationRouter: Router,
        private val userSearchItemStorage: UserSearchItemStorage,
        private val tabsProvider: TabsProvider
) : BaseNavigationPresenter<MainBottomNavigationView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.tabsProvider.getTabChangePublish().subscribe(SearchTabSubscriber())
    }
    fun replaceFragment(screenName: SupportAppScreen) {
        this.bottomNavigationRouter.replaceScreen(screenName)
    }

    override fun onBackPressed(): Boolean {
        this.bottomNavigationRouter.exit()
        return true
    }

    fun searchTextSubmit(text: String) {
        this.userSearchItemStorage.overrideCurrentSearchText(text)
    }

    fun initSearchState(tabContainer: TabContainer?) {
        this.viewState.initSearchTabNavigation(tabContainer)
    }

    private inner class SearchTabSubscriber : Subscriber<TabContainer>() {
        override fun onCompleted() {}
        override fun onNext(result: TabContainer?) {
            this@BottomNavigationPresenter.initSearchState(result)
        }

        override fun onError(e: Throwable) {}
    }
}