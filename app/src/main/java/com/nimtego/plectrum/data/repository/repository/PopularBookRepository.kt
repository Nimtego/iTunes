package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularBookMapper
import com.nimtego.plectrum.data.model.rss_itunes.Feed
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookKey
import com.nimtego.plectrum.domain.repository.RepositoryPopular
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
) : RepositoryPopular<BaseParentViewModel<ChildViewModel>> {

    override fun query(sectionKey: SectionsKey, responseSize: Int): Observable<BaseParentViewModel<ChildViewModel>> {
        return Observable.zip<PopularResponse, PopularResponse, BaseParentViewModel<ChildViewModel>>(
                topFreeBook(responseSize),
                topPaidBook(responseSize),
                BiFunction<PopularResponse, PopularResponse, BaseParentViewModel<ChildViewModel>>
                { freeBook, paidBook ->
                    BaseParentViewModel(listOf(
                            topParentModel(PopularBookKey.TOP_FREE_BOOK, freeBook.feed),
                            topParentModel(PopularBookKey.TOP_PAID_BOOK, paidBook.feed)
                        )
                    )
                }
        )
    }
    private fun topParentModel(bookKey: PopularBookKey, feed: Feed): ParentTabModelContainer<ChildViewModel> {
        return SectionViewModel(
                bookKey,
                feed.title,
                feed.results.map {
                    BookWrapperModel(mapper.popularResultToBook(it))
                })
    }


    private fun topFreeBook(responseSize: Int): Observable<PopularResponse> {
        return dataStoreFactory.topFreeBook(responseSize)
    }
    private fun topPaidBook(responseSize: Int): Observable<PopularResponse> {
        return dataStoreFactory.topPaidBook(responseSize)
    }
}