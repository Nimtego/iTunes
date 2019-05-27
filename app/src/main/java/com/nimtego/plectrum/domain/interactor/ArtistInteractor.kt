package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.ArtistModel
import io.reactivex.Observable


class ArtistInteractor : BaseInteractor<List<ArtistModel>, ArtistInteractor.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<ArtistModel>> {
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