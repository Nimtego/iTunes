package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.data.repository.repository.DashBoardRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DashBoardInteractorK @Inject constructor(
        disposable: CompositeDisposable,
        repository: DashBoardRepository
) : BaseInteractorK<DashBoardModel, DashBoardInteractorK.Params>(disposable, repository) {

    override fun buildUseCaseObservable(params: Params): Observable<DashBoardModel> {
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