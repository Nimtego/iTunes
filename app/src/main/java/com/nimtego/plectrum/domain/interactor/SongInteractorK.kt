package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.SongModel
import io.reactivex.Observable


class SongInteractorK : BaseInteractorK<List<SongModel>, SongInteractorK.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<SongModel>> {
        return repository.songs(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {
            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }

}