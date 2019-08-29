package com.nimtego.plectrum.presentation.mvp.presenters.search

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.MusicalSearchUseCase
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import com.nimtego.plectrum.presentation.mvp.model.song.SongWrapperModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BasePresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchContentView
import com.nimtego.plectrum.presentation.navigation.LocalHolder
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.MoreSectionAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SearchContentPresenter @Inject constructor(
        private val localHolder: LocalHolder,
        private val interactor: MusicalSearchUseCase,
        private val searchItemStorage: UserSearchItemStorage,
        private val userChoiceItemStorage: MainItemStorage,
        private val schedulersProvider: SchedulersProvider
) : BasePresenter<SearchContentView>(), MoreSectionAdapter.OnItemClickListener {

    private lateinit var navigationQualifier: String
    private var router: Router? = null
    private var dataModel: List<ChildViewModel>? = null
    private var currentSearchText: String? = null

    override fun onUserItemClicked(childViewModel: ChildViewModel) {
        this.userChoiceItemStorage.changeCurrentChildItem(childViewModel)
        this.router?.navigateTo(Screens.ItemInformationScreen(navigationQualifier))
    }

//    override fun attachView(view: MoreSectionView) {
//        super.attachView(view)
//        viewReady()
//    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestSearchData()
    }

    private fun requestSearchData() {
        if (this.searchItemStorage.getCurrentSearchText() != this.currentSearchText) {
            this.currentSearchText = this.searchItemStorage.getCurrentSearchText()
            val currentSearchObserver = CurrentSearchObserver()
            currentSearchObserver.connect()
            this.currentSearchText?.let {
                this.interactor.searchSong(it)
                        .observeOn(schedulersProvider.ui())
                        .doOnSubscribe {
                            this@SearchContentPresenter.viewState.showProgress(true)
                        }
                        .doAfterTerminate {
                            this@SearchContentPresenter.viewState.showProgress(false)
                        }
                        .subscribe(currentSearchObserver)
            }
        }
    }

    private fun showModel() {
        dataModel?.let {
            viewState.showViewState(it)
        }
    }

    fun onBackPressed() {
        this.router?.exit()
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = localHolder.getCicerone(navigationQualifier).router
    }

    private inner class CurrentSearchObserver : DisposableObserver<List<Song>>() {
        override fun onComplete() {

        }

        override fun onNext(content: List<Song>) {
            this@SearchContentPresenter.dataModel =
                    content.map { song -> SongWrapperModel(song) }
            this@SearchContentPresenter.showModel()
        }

        override fun onError(e: Throwable) {
            e.message?.let { this@SearchContentPresenter.viewState.systemMessage(it) }
        }
    }
}