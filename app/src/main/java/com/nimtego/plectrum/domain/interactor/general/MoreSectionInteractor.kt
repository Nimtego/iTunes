package com.nimtego.plectrum.domain.interactor.general

import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
import com.nimtego.plectrum.domain.interactor.base.BaseInteractor
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoreSectionInteractor @Inject constructor(
        disposable: CompositeDisposable,
        private val repository: MoreSectionRepository
) : BaseInteractor<ParentTabModelContainer<ChildViewModel>, MoreSectionInteractor.Params>(disposable) {

    override fun buildUseCaseObservable(params: Params): Observable<ParentTabModelContainer<ChildViewModel>> {
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