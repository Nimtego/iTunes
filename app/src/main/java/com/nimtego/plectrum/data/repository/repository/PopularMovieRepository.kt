package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularMovieMapper
import com.nimtego.plectrum.data.model.rss_itunes.Feed
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieDataStore
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.mvp.model.movie.MovieWrapperModel
import io.reactivex.Observable
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(
        private val dataStoreFactory: PopularMovieDataStore,
        private val mapper: PopularMovieMapper
) : Repository<BaseParentViewModel<ChildViewModel>> {

    override fun query(request: String, responseSize: Int): Observable<BaseParentViewModel<ChildViewModel>> {
        return dataStoreFactory.topMovie(responseSize).map {
            BaseParentViewModel(listOf(resultToMovie(it.feed)))
        }
    }

    private fun resultToMovie(feed: Feed): ParentTabModelContainer<ChildViewModel> {
        return SectionViewModel(feed.title,
                feed.results.map {
                    MovieWrapperModel(mapper.popularResultToMovie(it))
                })
    }
}