package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.data.repository.repository.DashBoardRepository
import io.reactivex.Observable

class DashBoardInteractorK(repository: DashBoardRepository)
    : BaseInteractorK<DashBoardModel, BaseInteractorK.Params>(repository) {

    override fun buildUseCaseObservable(params: Params): Observable<DashBoardModel> {
        return repository.query(params.request)
    }
}