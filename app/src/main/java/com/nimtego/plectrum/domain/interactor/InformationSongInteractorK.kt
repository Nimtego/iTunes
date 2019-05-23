package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK
import dagger.internal.Preconditions
import io.reactivex.Observable

class InformationSongInteractorK : BaseInteractorK<SongDetailsModelK, InformationSongInteractorK.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<SongDetailsModelK> {
        return repository.songDeteil(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }
}