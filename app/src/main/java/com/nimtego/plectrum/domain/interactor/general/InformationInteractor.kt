package com.nimtego.plectrum.domain.interactor.general

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.domain.interactor.base.BaseInteractor
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class InformationInteractor @Inject constructor(
        disposable: CompositeDisposable,
        private val repositoryBookRepository: PopularBookRepository
) : BaseInteractor<BaseParentViewModel<ChildViewModel>, InformationInteractor.Params>(disposable) {

    override fun buildUseCaseObservable(params: Params): Observable<BaseParentViewModel<ChildViewModel>> {
        return repositoryBookRepository.query(params.request, 0)
    }

    class Params private constructor(
            val request: SectionsKey,
            val responseSize: Int) {

        companion object {

            fun forRequest(request: SectionsKey): Params {
                return forRequestWithSize(request, 0)
            }

            fun forRequestWithSize(request: SectionsKey, responseSize: Int): Params {
                return Params(request, responseSize)
            }

        }
    }
}