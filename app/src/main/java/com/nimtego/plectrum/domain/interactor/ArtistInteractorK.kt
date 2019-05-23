package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.ArtistModelK
import dagger.internal.Preconditions
import io.reactivex.Observable


class ArtistInteractorK : BaseInteractorK<List<ArtistModelK>, ArtistInteractorK.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<ArtistModelK>> {
//        Preconditions.checkNotNull(params)
        return repository.artists(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }

}