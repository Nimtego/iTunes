package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel
import io.reactivex.Observable

class InformationAlbumInteractor : BaseInteractor<AlbumDetailsModel, InformationAlbumInteractor.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<AlbumDetailsModel> {
        return repository.albumDeteil(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }
}
