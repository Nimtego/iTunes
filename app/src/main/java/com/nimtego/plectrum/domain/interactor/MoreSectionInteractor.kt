package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
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