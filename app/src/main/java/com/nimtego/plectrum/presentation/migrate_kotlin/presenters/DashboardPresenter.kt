package com.nimtego.plectrum.presentation.migrate_kotlin.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.presentation.migrate_kotlin.Screens
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.DashBoardView
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router


@InjectViewState
class DashboardPresenter(router: Router?,
                         screenNumber: Int,
                         private val interactor: DashBoardInteractor)
    : BasePresenter<DashBoardView>(router, screenNumber) {

    fun viewIsReady() {
        interactor.execute(object : DisposableObserver<DashBoardModel>() {
            override fun onComplete() {
                Log.i("Presenter", "onComplete()")
            }

            override fun onNext(dataModel: DashBoardModel) {
                Log.i("Presenter", "onnext")
                this@DashboardPresenter.showModel(dataModel)
            }

            override fun onError(e: Throwable) {
                Log.i("Presenter", "onerror $e")
//                this@DashboardPresenter.hideViewLoading()
//                this@DashboardPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

            }
        }, DashBoardInteractor.Params.forRequest(""))
    }

    private fun showModel(dataModel: DashBoardModel) {
        viewState.showViewState(dataModel)
    }

    fun albumClicked(albumModel: Album) {
        router?.navigateTo(Screens.AlbumInformationDetail(albumModel.albumId.toString()))
    }

    fun songClicked(songModel: Song) {
        router?.navigateTo(Screens.SongInformationDetail(songModel.trackId.toString()))
    }

}