package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.DashBoardModelContainer
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.SectionViewModel
import com.nimtego.plectrum.presentation.navigation.Screens
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router

@InjectViewState
class TabContentPresenter(router: Router,
                          screenNumber: Int,
                          private val interactor: DashBoardInteractor)
    : BasePresenter<TabContentView>(router, screenNumber) {

    private var dataModel: DashBoardModel? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewIsReady()
    }

    fun viewIsReady() {
        dataModel?.let { showModel(it) }.run {
            interactor.execute(object : DisposableObserver<DashBoardModel>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(dataModel: DashBoardModel) {
                    Log.i("Presenter", "onnext")
                    this@TabContentPresenter.dataModel = dataModel
                    this@TabContentPresenter.showModel(dataModel)
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@DashboardPresenter.hideViewLoading()
//                this@DashboardPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, DashBoardInteractor.Params.forRequest(""))
        }
    }

    private fun showModel(dataModel: DashBoardModel) {
        //todo create res for title or...
        val data = BaseParentViewModel(listOf<DashBoardModelContainer<ChildViewModel>>(
                SectionViewModel("Top album", dataModel.topAlbums),
                SectionViewModel("Top song", dataModel.topSongs),
                SectionViewModel("Hot song", dataModel.hotTrack),
                SectionViewModel("New music", dataModel.newMusic))
        )

        viewState.showViewState(data)
    }

    fun albumClicked(albumModel: Album) {
        router.navigateTo(Screens.AlbumInformationDetail(albumModel.albumId.toString()))
    }

    fun songClicked(songModel: Song) {
        router.navigateTo(Screens.SongInformationDetail(songModel.trackId.toString()))
    }
}
