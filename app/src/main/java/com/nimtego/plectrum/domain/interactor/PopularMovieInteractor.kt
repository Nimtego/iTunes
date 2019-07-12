package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.presentation.mvp.model.song.MusicTabModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMovieInteractor@Inject constructor (
        disposable: CompositeDisposable,
        repository: PopularMusicRepository
) : BaseInteractor<List<MusicTabModel>, PopularMovieInteractor.Params>(disposable, repository) {

    override fun buildUseCaseObservable(params: Params): Observable<List<MusicTabModel>> {
        return repository.query(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}