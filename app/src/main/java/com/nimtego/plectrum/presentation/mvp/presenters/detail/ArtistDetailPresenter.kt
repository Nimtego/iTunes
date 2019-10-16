package com.nimtego.plectrum.presentation.mvp.presenters.detail

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.detail.ArtistDetailUseCase
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistDetailModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.detail.ArtistDetailView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class ArtistDetailPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val artistDetailUseCase: ArtistDetailUseCase,
        private val itemStorage: ChildItemStorage,
        private val schedulersProvider: SchedulersProvider
) : BaseContentPresenter<ArtistDetailView>() {

    override lateinit var router: Router

    private lateinit var navigationQualifier: String
    private var dataDetailModel: ArtistDetailModel? = null


    override fun prepareViewModel() {
        this.dataDetailModel ?: run {
            this.itemStorage.getCurrentChildItem()?.let {
                this.artistDetailUseCase.artistModelById(it.id())
                        .observeOn(schedulersProvider.ui())
                        .doOnSubscribe {
                            this@ArtistDetailPresenter.viewState.showProgress(true)
                        }
                        .doAfterTerminate {
                            this@ArtistDetailPresenter.viewState.showProgress(false)
                        }
                        .subscribe(
                                { songModel ->
                                    this@ArtistDetailPresenter.dataDetailModel = songModel
                                    this@ArtistDetailPresenter.showModel()
                                },
                                //todo throwable state
                                {}
                        ).connect()
            }
        }
    }

    private fun showModel() {
        dataDetailModel?.let {
            viewState.showViewState(it)
        }
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = navigationHandler.getRouter(navigationQualifier)
    }

}