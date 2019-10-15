package com.nimtego.plectrum.presentation.mvp.presenters.detail

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.detail.SongDetailUseCase
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.detail.SongDetailView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SongDetailPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val songDetailUseCase: SongDetailUseCase,
        private val itemStorage: ChildItemStorage,
        private val schedulersProvider: SchedulersProvider
) : BaseContentPresenter<SongDetailView>() {

    override lateinit var router: Router

    private lateinit var navigationQualifier: String
    private var dataModel: SongModel? = null

    override fun prepareViewModel() {
        this.dataModel ?: run {
            this.itemStorage.getCurrentChildItem()?.let {
                this.songDetailUseCase.songModelById(it.id())
                        .observeOn(schedulersProvider.ui())
                        .doOnSubscribe {
                            this@SongDetailPresenter.viewState.showProgress(true)
                        }
                        .doAfterTerminate {
                            this@SongDetailPresenter.viewState.showProgress(false)
                        }
                        .subscribe(
                                { songModel ->
                                    this@SongDetailPresenter.dataModel = songModel
                                    this@SongDetailPresenter.showModel()
                                },
                                //todo throwable state
                                {throwable -> this@SongDetailPresenter.viewState.systemMessage("${throwable}")}
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