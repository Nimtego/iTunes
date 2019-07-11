package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.mvp.model.song.Song
import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoreSectionInteractor @Inject constructor (
        disposable: CompositeDisposable,
        repository: MoreSectionRepository
) : BaseInteractor<List<Song>, MoreSectionInteractor.Params>(disposable, repository) {

    override fun buildUseCaseObservable(params: Params): Observable<List<Song>> {
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