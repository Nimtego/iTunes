package com.nimtego.plectrum.domain.interactor.popular

import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.domain.interactor.base.BaseInteractor
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularBookInteractor @Inject constructor(
        disposable: CompositeDisposable,
        private val repository: PopularBookRepository
) : BaseInteractor<BaseParentViewModel<ChildViewModel>, PopularBookInteractor.Params>(disposable) {

    override fun buildUseCaseObservable(params: Params): Observable<BaseParentViewModel<ChildViewModel>> {
        return repository.query(params.request, params.responseSize)
    }

    class Params private constructor(
            val request: String,
            val responseSize: Int) {

        companion object {

            fun forRequest(request: String): Params {
                return forRequestWithSize(request, 100)
            }

            fun forRequestWithSize(request: String, responseSize: Int): Params {
                return Params(request, responseSize)
            }

        }
    }

}