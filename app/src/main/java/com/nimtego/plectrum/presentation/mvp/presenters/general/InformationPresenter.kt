package com.nimtego.plectrum.presentation.mvp.presenters.general

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.general.InformationInteractor
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.information_view.SongDetailsModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BasePresenter
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InformationPresenter
@Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val interactor: InformationInteractor,
        private val itemStorage: ChildItemStorage
) : BasePresenter<InformationView>() {

    private lateinit var navigationQualifier: String
    private var router: Router? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.itemStorage.getCurrentChildItem()?.let {
            viewState.showViewState(it)
        }
    }

    fun onBackPressed() {
        this.router?.exit()
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = navigationHandler.getRouter(navigationQualifier)
    }

}