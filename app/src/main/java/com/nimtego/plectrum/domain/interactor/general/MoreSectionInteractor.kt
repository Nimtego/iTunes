package com.nimtego.plectrum.domain.interactor.general

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookKey
import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
import com.nimtego.plectrum.domain.interactor.base.BaseInteractor
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoreSectionInteractor @Inject constructor(
        disposable: CompositeDisposable,
        private val repository: MoreSectionRepository
) : BaseInteractor<SectionViewModel<ChildViewModel>, MoreSectionInteractor.Params>(disposable) {

    override fun buildUseCaseObservable(params: Params): Observable<SectionViewModel<ChildViewModel>> {
        return repository.query(params.request, params.responseSize)
    }

    class Params private constructor(
            val request: SectionsKey,
            val responseSize: Int) {

        companion object {

            fun forRequest(request: SectionsKey): Params {
                return forRequestWithSize(request, 100)
            }

            fun forRequestWithSize(request: SectionsKey, responseSize: Int): Params {
                return Params(request, responseSize)
            }

        }
    }

}