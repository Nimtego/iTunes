package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMusicInteractor @Inject constructor (
        disposable: CompositeDisposable,
        repository: PopularMusicRepository
) : BaseInteractor<TabContentModel, TabContentInteractor.Params>(disposable, repository) {
    override fun buildUseCaseObservable(params: Params): Observable<TabContentModel> {
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