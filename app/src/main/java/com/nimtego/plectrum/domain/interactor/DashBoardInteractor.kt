package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.DashBoardModel
import io.reactivex.Observable

class DashBoardInteractor : BaseInteractor<DashBoardModel, DashBoardInteractor.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<DashBoardModel> {
        return repository.dashBoardModel()
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}