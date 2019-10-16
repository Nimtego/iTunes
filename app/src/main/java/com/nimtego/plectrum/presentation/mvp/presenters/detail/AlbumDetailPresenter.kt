package com.nimtego.plectrum.presentation.mvp.presenters.detail

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.detail.AlbumDetailUseCase
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import com.nimtego.plectrum.presentation.mvp.model.song.AlbumWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.song.SongWrapperModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.detail.AlbumDetailView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.Screens
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
    private var albumDetailModel: AlbumDetailModel? = null


    override fun prepareViewModel() {
        this.itemStorage.getCurrentChildItem()?.let {
            println(it.id())
        }
        this.albumDetailModel ?: run {
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
                                    this@AlbumDetailPresenter.albumDetailModel = model
                                    this@AlbumDetailPresenter.showModel()
                                },
                                //todo throwable state
                                { throwable -> this@AlbumDetailPresenter.viewState.systemMessage("${throwable.message}") }
                        ).connect()
            }
        }
    }

    private fun showModel() {
        albumDetailModel?.let {
            viewState.showViewState(it)
        }
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = navigationHandler.getRouter(navigationQualifier)
    }

    fun songClicked(songDetailModel: SongDetailModel) {
        //todo implement
       // this.itemStorage.changeCurrentChildItem(songDetailModel)
        //this.router.navigateTo(Screens.SongDetailScreen(navigationQualifier))
    }

}