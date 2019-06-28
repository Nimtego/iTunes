package com.nimtego.plectrum.presentation.old.information_view.song

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.InformationSongInteractor
import com.nimtego.plectrum.presentation.base.BasePresenter
import com.nimtego.plectrum.presentation.old.information_view.song.model.SongDetailsModel
import io.reactivex.observers.DisposableObserver

@InjectViewState
class SongInformationPresenterImpl (val interactor: InformationSongInteractor = InformationSongInteractor())
    : BasePresenter<SongInformationView>(), SongInformationPresenter {

    override fun viewReady(songNameForResponse: String) {
        interactor.execute(object : DisposableObserver<SongDetailsModel>() {
            override fun onNext(songDetailsModel: SongDetailsModel) {
                this@SongInformationPresenterImpl.showSongInView(songDetailsModel)
            }

            override fun onError(e: Throwable) {
                //todo
//                viewState.toast(e.javaClass.canonicalName + e.message)
//                viewState.hideLoading()
            }

            override fun onComplete() {

            }
        }, InformationSongInteractor.Params.forRequest(songNameForResponse))
    }

    private fun showSongInView(songDetailsModel: SongDetailsModel) {
        //todo
        //viewState.toast(songDetailsModel.songName)
        viewState.render(songDetailsModel)
    }
}