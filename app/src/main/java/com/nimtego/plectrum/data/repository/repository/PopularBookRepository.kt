package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularBookMapper
import com.nimtego.plectrum.data.model.rss_itunes.Feed
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookDataStore
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.mvp.model.book.BookWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class PopularBookRepository @Inject constructor(
        private val dataStoreFactory: PopularBookDataStore,
        private val mapper: PopularBookMapper
) : Repository<BaseParentViewModel<ChildViewModel>> {

    override fun query(request: String): Observable<BaseParentViewModel<ChildViewModel>> {
        return Observable.zip<PopularResponse, PopularResponse, BaseParentViewModel<ChildViewModel>>(
                topFreeBook(),
                topPaidBook(),
                BiFunction<PopularResponse, PopularResponse, BaseParentViewModel<ChildViewModel>>
                { freeBook, paidBook ->
                    BaseParentViewModel(listOf(topParentModel(freeBook.feed),
                                               topParentModel(paidBook.feed)))
                }
        )
    }
    private fun topParentModel(feed: Feed): ParentTabModelContainer<ChildViewModel> {
        return SectionViewModel(feed.title,
                feed.results.map {
                    BookWrapperModel(mapper.popularResultToBook(it))
                })
    }


    private fun topFreeBook(): Observable<PopularResponse> {
        return dataStoreFactory.topFreeBook()
    }
    private fun topPaidBook(): Observable<PopularResponse> {
        return dataStoreFactory.topPaidBook()
    }
}