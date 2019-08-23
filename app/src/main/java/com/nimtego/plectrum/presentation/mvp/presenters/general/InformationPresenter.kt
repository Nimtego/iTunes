package com.nimtego.plectrum.presentation.mvp.presenters.general

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.general.InformationInteractor
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.information_view.SongDetailsModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BasePresenter
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InformationPresenter
@Inject constructor(
        private val routerHandler: Map<String, Cicerone<Router>>,
        private val interactor: InformationInteractor,
        private val itemStorage: ChildItemStorage
) : BasePresenter<InformationView>() {

    private lateinit var navigationQualifier: String
    private var router: Router? = null
    private var dataSongsModel: SongDetailsModel? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.itemStorage.getCurrentChildItem()?.let {
            viewState.showViewState(it)
        } ?: run{ viewState.systemMessage("NULL")}
    }

    fun onBackPressed() {
        this.router?.exit()
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = routerHandler[navigationQualifier]?.router
    }

}