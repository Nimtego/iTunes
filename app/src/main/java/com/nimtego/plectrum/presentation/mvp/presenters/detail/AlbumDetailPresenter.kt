package com.nimtego.plectrum.presentation.mvp.presenters.detail

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.detail.AlbumDetailUseCase
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.detail.AlbumDetailView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AlbumDetailPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val albumDetailUseCase: AlbumDetailUseCase,
        private val itemStorage: ChildItemStorage,
        private val schedulersProvider: SchedulersProvider
) : BaseContentPresenter<AlbumDetailView>() {

    override lateinit var router: Router

    private lateinit var navigationQualifier: String
    private var dataModel: AlbumModel? = null


    override fun prepareViewModel() {
        this.itemStorage.getCurrentChildItem()?.let{
            println(it.id())
        }
        this.dataModel ?: run {
            this.itemStorage.getCurrentChildItem()?.let {
                this.albumDetailUseCase.albumModelById(it.id())
                        .observeOn(schedulersProvider.ui())
                        .doOnSubscribe {
                            this@AlbumDetailPresenter.viewState.showProgress(true)
                        }
                        .doAfterTerminate {
                            this@AlbumDetailPresenter.viewState.showProgress(false)
                        }
                        .subscribe(
                                { model ->
                                    this@AlbumDetailPresenter.dataModel = model
                                    this@AlbumDetailPresenter.showModel()
                                    this@AlbumDetailPresenter.viewState.systemMessage(model.albumArtistName)
                                },
                                //todo throwable state
                                {throwable -> this@AlbumDetailPresenter.viewState.systemMessage("${throwable.message}")}
                        ).connect()
            }
        }
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