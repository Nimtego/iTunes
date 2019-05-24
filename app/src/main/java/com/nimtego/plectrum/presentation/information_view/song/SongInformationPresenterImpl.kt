package com.nimtego.plectrum.presentation.information_view.song

import com.nimtego.plectrum.domain.interactor.InformationSongInteractor
import com.nimtego.plectrum.domain.interactor.InformationSongInteractorK
import com.nimtego.plectrum.presentation.base.BaseContract
import com.nimtego.plectrum.presentation.base.BasePresenter
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK
import io.reactivex.observers.DisposableObserver

class SongInformationPresenterImpl (val interactor: InformationSongInteractorK = InformationSongInteractorK())
    : BasePresenterK<SongInformationView>(), SongInformationPresenterK {

    override fun viewReady(songNameForResponse: String) {
        interactor.execute(object : DisposableObserver<SongDetailsModelK>() {
            override fun onNext(songDetailsModel: SongDetailsModelK) {
                this@SongInformationPresenterImpl.showSongInView(songDetailsModel)
            }

            override fun onError(e: Throwable) {
                //todo
//                viewState.toast(e.javaClass.canonicalName + e.message)
//                viewState.hideLoading()
            }

            override fun onComplete() {

            }
        }, InformationSongInteractorK.Params.forRequest(songNameForResponse))
    }

    private fun showSongInView(songDetailsModel: SongDetailsModelK) {
        //todo
//        viewState.toast(songDetailsModel.songName)
//        viewState.render(songDetailsModel)
    }
}