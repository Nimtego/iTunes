package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookKey
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicKey
import com.nimtego.plectrum.domain.repository.RepositoryPopular
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import com.nimtego.plectrum.presentation.mvp.model.movie.MovieWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.song.*
import io.reactivex.Observable
import java.lang.Exception

class MoreSectionRepository(
        private val musicDataStoreFactory: PopularMusicDataStore,
        private val bookDataStoreFactory: PopularBookDataStore,
        private val movieDataStoreFactory: PopularMovieDataStore,
        private val mapper: PopularMusicMapper
) : RepositoryPopular<SectionViewModel<ChildViewModel>> {

    override fun query(sectionKey: SectionsKey,
                       responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        //todo remove "request,request," - ugly
        return when (sectionKey) {
            is PopularMusicKey -> getMusicalContent(sectionKey, responseSize)
            is PopularMovieKey -> getMovieContent(sectionKey, responseSize)
            is PopularBookKey  -> getBookContent(sectionKey, responseSize)
            else -> throw Exception("Section invalid")
        }
    }

    private fun getMusicalContent(
            keySection: PopularMusicKey,
            responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        val contentObservableByKey = when(keySection) {
            PopularMusicKey.TOP_TRACK -> musicDataStoreFactory.topTrack(responseSize)
            PopularMusicKey.HOT_TRACK -> musicDataStoreFactory.hotTrack(responseSize)
            PopularMusicKey.NEW_TRACK -> musicDataStoreFactory.newTrack(responseSize)
            PopularMusicKey.TOP_ALBUM -> musicDataStoreFactory.topAlbum(responseSize)
        }
        return contentObservableByKey.map {
            val modelList : List<ChildViewModel> = it.feed.results.map {
                result: Result -> mapper.popularResultToMusicalModel(result)
            }.map { song: Song -> SongWrapperModel(song) }
            SectionViewModel(titleKey = keySection,
                             title = it.feed.title,
                             parentList = modelList
            )
        }
    }

    //todo Prepare wrapper for book and movie!
    
    private fun getMovieContent(
            keySection: PopularMovieKey,
            responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        val contentObservableByKey = when(keySection) {
            PopularMovieKey.TOP_MOVIE -> movieDataStoreFactory.topMovie(responseSize)
        }
        return contentObservableByKey.map {
            val modelList : List<ChildViewModel> = it.feed.results.map {
                result: Result -> mapper.popularResultToMusicalModel(result)
            }.map { movie: Song -> SongWrapperModel(movie) }
            SectionViewModel(titleKey = keySection,
                    title = it.feed.title,
                    parentList = modelList
            )
        }
    }

    private fun getBookContent(
            keySection: PopularBookKey,
            responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        val contentObservableByKey = when(keySection) {
            PopularBookKey.TOP_PAID_BOOK -> bookDataStoreFactory.topPaidBook(responseSize)
            PopularBookKey.TOP_FREE_BOOK -> bookDataStoreFactory.topFreeBook(responseSize)
        }
        return contentObservableByKey.map {
            val modelList : List<ChildViewModel> = it.feed.results.map {
                result: Result -> mapper.popularResultToMusicalModel(result)
            }.map { movie: Song -> SongWrapperModel(movie) }
            SectionViewModel(titleKey = keySection,
                    title = it.feed.title,
                    parentList = modelList
            )
        }
    }
}