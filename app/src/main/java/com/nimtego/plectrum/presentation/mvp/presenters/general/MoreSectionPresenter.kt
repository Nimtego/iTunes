package com.nimtego.plectrum.presentation.mvp.presenters.general

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.general.MoreSectionInteractor
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BasePresenter
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.MoreSectionAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MoreSectionPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val interactor: MoreSectionInteractor,
        private val itemStorage: MainItemStorage
) : BasePresenter<MoreSectionView>(), MoreSectionAdapter.OnItemClickListener {

    private lateinit var navigationQualifier: String
    private var router: Router? = null
    private var dataModel: ParentTabModelContainer<ChildViewModel>? = null

    override fun onUserItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.router?.navigateTo(Screens.ItemInformationScreen(navigationQualifier))
    }

    override fun attachView(view: MoreSectionView) {
        super.attachView(view)
        prepareViewModel()
    }

    private fun prepareViewModel() {
        this.dataModel?.let { showModel() } ?: run { executeModel() }
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

    fun onBackPressed() {
        this.router?.exit()
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = navigationHandler.getRouter(navigationQualifier)
    }
}