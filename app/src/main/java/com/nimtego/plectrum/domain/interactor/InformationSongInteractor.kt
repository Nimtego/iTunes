package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.mvp.view_model.information_view.SongDetailsModel
import io.reactivex.Observable

class InformationSongInteractor : BaseInteractor<SongDetailsModel, InformationSongInteractor.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<SongDetailsModel> {
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