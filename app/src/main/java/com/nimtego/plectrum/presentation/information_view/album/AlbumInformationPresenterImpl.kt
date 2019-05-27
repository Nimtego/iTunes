package com.nimtego.plectrum.presentation.information_view.album

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.InformationAlbumInteractor
import com.nimtego.plectrum.presentation.base.BasePresenter
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel
import io.reactivex.observers.DisposableObserver

@InjectViewState
class AlbumInformationPresenterImpl (val interactor: InformationAlbumInteractor = InformationAlbumInteractor())
    : BasePresenter<AlbumInformationView>(), AlbumInformationPreseneter {

    override fun viewReady(albumNameForResponse: String) {
        interactor.execute(object : DisposableObserver<AlbumDetailsModel>() {
            override fun onNext(albumDetailsModel: AlbumDetailsModel) {
                this@AlbumInformationPresenterImpl.showAlbumsInView(albumDetailsModel)
            }

            override fun onError(e: Throwable) {
                //todo
//                viewState.toast(e.javaClass.canonicalName + e.message)
//                viewState.hideLoading()
            }

            override fun onComplete() {

            }
        }, InformationAlbumInteractor.Params.forRequest(albumNameForResponse))
    }

    private fun showAlbumsInView(albumDetailsModel: AlbumDetailsModel) {
        //todo
//        viewState.toast(albumDetailsModel.albumName)
//        this.view.render(albumDetailsModel)
    }
}
