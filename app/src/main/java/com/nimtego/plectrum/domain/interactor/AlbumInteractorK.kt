package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import dagger.internal.Preconditions
import io.reactivex.Observable


class AlbumInteractorK : BaseInteractorK<List<AlbumModelK>, AlbumInteractorK.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<List<AlbumModelK>> {
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