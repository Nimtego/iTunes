package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.DashBoardSongsModel
import com.nimtego.plectrum.data.repository.repository.DashBoardRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class BottomNavigationInteractor @Inject constructor(
        disposable: CompositeDisposable,
        repository: DashBoardRepository
) : BaseInteractor<DashBoardSongsModel, BottomNavigationInteractor.Params>(disposable, repository) {

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