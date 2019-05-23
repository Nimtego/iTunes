package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK
import io.reactivex.Observable

class InformationAlbumInteractorK : BaseInteractorK<AlbumDetailsModelK, InformationAlbumInteractorK.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<AlbumDetailsModelK> {
//        Preconditions.checkNotNull(params)
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
