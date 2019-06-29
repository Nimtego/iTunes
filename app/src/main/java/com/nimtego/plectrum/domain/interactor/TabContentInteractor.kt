package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.DashBoardSongsModel
import com.nimtego.plectrum.data.repository.repository.TabContentRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TabContentInteractor @Inject constructor (
        disposable: CompositeDisposable,
        repository: TabContentRepository
) : BaseInteractorK<DashBoardSongsModel, TabContentInteractor.Params>(disposable, repository) {
    override fun buildUseCaseObservable(params: Params): Observable<DashBoardSongsModel> {
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