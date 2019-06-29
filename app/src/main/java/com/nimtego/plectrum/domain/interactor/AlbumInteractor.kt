package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.old.main.model.AlbumModel
import io.reactivex.Observable


class AlbumInteractor : BaseInteractor<List<AlbumModel>, AlbumInteractor.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<List<AlbumModel>> {
//        Preconditions.checkNotNull(params)
        return repository.albums(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}