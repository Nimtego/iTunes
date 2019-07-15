package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularBookInteractor @Inject constructor (
        disposable: CompositeDisposable,
        repository: PopularBookRepository
) : BaseInteractor<BaseParentViewModel<ChildViewModel>, PopularBookInteractor.Params>(disposable, repository) {

    override fun buildUseCaseObservable(params: Params): Observable<BaseParentViewModel<ChildViewModel>> {
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