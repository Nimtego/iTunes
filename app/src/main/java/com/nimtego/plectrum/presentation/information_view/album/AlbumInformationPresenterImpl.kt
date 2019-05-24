package com.nimtego.plectrum.presentation.information_view.album

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.InformationAlbumInteractorK
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK
import io.reactivex.observers.DisposableObserver

@InjectViewState
class AlbumInformationPresenterImpl (val interactor: InformationAlbumInteractorK = InformationAlbumInteractorK())
    : BasePresenterK<AlbumInformationViewK>(), AlbumInformationPreseneterK {

    override fun viewReady(albumNameForResponse: String) {
        interactor.execute(object : DisposableObserver<AlbumDetailsModelK>() {
            override fun onNext(albumDetailsModel: AlbumDetailsModelK) {
                this@AlbumInformationPresenterImpl.showAlbumsInView(albumDetailsModel)
            }

            override fun onError(e: Throwable) {
                //todo
//                viewState.toast(e.javaClass.canonicalName + e.message)
//                viewState.hideLoading()
            }

            override fun onComplete() {

            }
        }, InformationAlbumInteractorK.Params.forRequest(albumNameForResponse))
    }

    private fun showAlbumsInView(albumDetailsModel: AlbumDetailsModelK) {
        //todo
//        viewState.toast(albumDetailsModel.albumName)
//        this.view.render(albumDetailsModel)
    }
}
