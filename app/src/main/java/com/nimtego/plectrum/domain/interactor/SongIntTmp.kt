package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.SongModel
import dagger.internal.Preconditions
import io.reactivex.Observable

class SongIntTmp : BaseInteractor<SongModel, SongIntTmp.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<SongModel> {
        Preconditions.checkNotNull<Params>(params)
        return repository.songs(params.request).map({ s -> s.get(0) })
    }

    class Params private constructor(val request: String) {
        companion object {
            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }
}
