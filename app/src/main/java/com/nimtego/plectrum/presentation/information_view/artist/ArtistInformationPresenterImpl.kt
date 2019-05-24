package com.nimtego.plectrum.presentation.information_view.artist

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.InformationArtistInteractorK
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import io.reactivex.observers.DisposableObserver

@InjectViewState
class ArtistInformationPresenterImpl(val interactor: InformationArtistInteractorK = InformationArtistInteractorK())
    : BasePresenterK<ArtistInformationViewK>(), ArtistInformationPresenterK {
    override fun viewReady(artistNameForResponse: String) {
        interactor.execute(object : DisposableObserver<ArtistDetailsModelK>() {
            override fun onNext(artistDetailsModel: ArtistDetailsModelK) {
                this@ArtistInformationPresenterImpl.showArtistInView(artistDetailsModel)
            }

            override fun onError(e: Throwable) {
                //todo
//                view.toast(e.javaClass.canonicalName + e.message + "\n"
//                        + Arrays.toString(e.stackTrace))
//                viewState.hideLoading()
            }

            override fun onComplete() {

            }
        }, InformationArtistInteractorK.Params.forRequest(artistNameForResponse))
    }

    override fun albumClicked(album: AlbumModelK) {
        //todo
//        viewState.toast(album.albumName)
    }

    private fun showArtistInView(artistDetailsModel: ArtistDetailsModelK) {
        //todo
//        viewState.toast(artistDetailsModel.artistName
//                + "\n" + artistDetailsModel.albums!!.size)
//        this.viewState.render(artistDetailsModel)
    }
}
