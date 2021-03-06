package com.nimtego.plectrum.presentation.mvp.presenters.general

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.repository.datasource.search.SongDataStoreFactory
import com.nimtego.plectrum.domain.interactor.general.MoreSectionInteractor
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.model.book.BookWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.AlbumWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.song.SongWrapperModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.SectionChildAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MoreSectionPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val interactor: MoreSectionInteractor,
        private val itemStorage: MainItemStorage
) : BaseContentPresenter<MoreSectionView>(), SectionChildAdapter.OnItemClickListener {

    private lateinit var navigationQualifier: String
    override lateinit var router: Router
    private var dataModel: ParentTabModelContainer<ChildViewModel>? = null

    override fun onUserItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.router.navigateTo(when(childViewModel) {
            is SongWrapperModel  -> Screens.SongDetailScreen(navigationQualifier)
            is AlbumWrapperModel -> Screens.AlbumDetailScreen(navigationQualifier)
            else                 -> Screens.ItemInformationScreen(navigationQualifier)
        })
    }

    override fun attachView(view: MoreSectionView) {
        super.attachView(view)
        if (!isInRestoreState(view)) {
            prepareViewModel()
        }
    }

    override fun prepareViewModel() {
        this.dataModel ?: run { executeModel() }
    }

    private fun executeModel() {
        interactor.execute(object : DisposableObserver<SectionViewModel<ChildViewModel>>() {
            override fun onNext(viewModel: SectionViewModel<ChildViewModel>) {
                this@MoreSectionPresenter.dataModel = viewModel
                this@MoreSectionPresenter.showModel()
            }

            override fun onComplete() {}
            //todo need impl. error handler
            override fun onError(e: Throwable) {}
        }, MoreSectionInteractor.Params.forRequestWithSize(
                this.itemStorage.getCurrentSection()!!.titleKey(),
                100)
        )
    }

    private fun showModel() {
        dataModel?.let { viewState.showViewState(it) }
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = navigationHandler.getRouter(navigationQualifier)
    }
}