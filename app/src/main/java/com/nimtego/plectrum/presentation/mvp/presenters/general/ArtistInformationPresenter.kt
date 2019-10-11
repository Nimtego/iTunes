package com.nimtego.plectrum.presentation.mvp.presenters.general

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.general.InformationInteractor
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.ArtistInformationView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class ArtistInformationPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val interactor: InformationInteractor,
        private val itemStorage: ChildItemStorage
) : BaseContentPresenter<ArtistInformationView>() {

    override lateinit var router: Router

    private lateinit var navigationQualifier: String
    private var dataModel: ArtistModel? = null


    override fun prepareViewModel() {
        this.dataModel ?: run {
            this.itemStorage.getCurrentChildItem()?.let {
                // this.dataModel = it

            }
        }
        showModel()
    }

    private fun showModel() {
        dataModel?.let {
            viewState.showViewState(it)
        }
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = navigationHandler.getRouter(navigationQualifier)
    }

}