package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularBookMapper
import com.nimtego.plectrum.data.model.mappers.PopularMovieMapper
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
import com.nimtego.plectrum.presentation.mvp.model.book.Book
import com.nimtego.plectrum.presentation.mvp.model.book.BookWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.mvp.model.movie.MovieWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.movie.PopularMovieModel
import com.nimtego.plectrum.presentation.mvp.model.song.AlbumWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.song.SongWrapperModel
import io.reactivex.Observable

//todo it is too much arguments
class MoreSectionRepository(
        private val musicDataStoreFactory: PopularMusicDataStore,
        private val bookDataStoreFactory: PopularBookDataStore,
        private val movieDataStoreFactory: PopularMovieDataStore,
        private val mapperMusic: PopularMusicMapper,
        private val mapperMovie: PopularMovieMapper,
        private val mapperBook: PopularBookMapper
) : RepositoryPopular<SectionViewModel<ChildViewModel>> {

    override fun query(sectionKey: SectionsKey,
                       responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        return when (sectionKey) {
            is PopularMusicKey -> getMusicalContent(sectionKey, responseSize)
            is PopularMovieKey -> getMovieContent(sectionKey, responseSize)
            is PopularBookKey -> getBookContent(sectionKey, responseSize)
            else -> throw Exception("Section invalid")
        }
    }

    private fun getMusicalContent(
            keySection: PopularMusicKey,
            responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        val contentObservableByKey = when (keySection) {
            PopularMusicKey.TOP_TRACK -> musicDataStoreFactory.topTrack(responseSize)
            PopularMusicKey.HOT_TRACK -> musicDataStoreFactory.hotTrack(responseSize)
            PopularMusicKey.NEW_TRACK -> musicDataStoreFactory.newTrack(responseSize)
            PopularMusicKey.TOP_ALBUM -> musicDataStoreFactory.topAlbum(responseSize)
        }
        return contentObservableByKey.map { response ->
            val modelList: List<ChildViewModel> = wrapModelByKey(keySection, response.feed.results)
            SectionViewModel(titleKey = keySection,
                    title = response.feed.title,
                    parentList = modelList
            )
        }
    }

    private fun wrapModelByKey(key: PopularMusicKey, result: List<Result>): List<ChildViewModel> {
        return when (key) {
            PopularMusicKey.TOP_ALBUM -> result.map {
                AlbumWrapperModel(mapperMusic.popularResultToAlbum(it))
            }
            else -> result.map { SongWrapperModel(mapperMusic.popularResultToMusicalModel(it)) }
        }
    }

    private fun getMovieContent(
            keySection: PopularMovieKey,
            responseSize: Int
    ): Observable<SectionViewModel<ChildViewModel>> {
        val contentObservableByKey = when (keySection) {
            PopularMovieKey.TOP_MOVIE -> movieDataStoreFactory.topMovie(responseSize)
        }
        return contentObservableByKey.map {
            val modelList: List<ChildViewModel> = it.feed.results.map { result: Result ->
                mapperMovie.popularResultToMovie(result)
            }.map { movie: PopularMovieModel -> MovieWrapperModel(movie) }
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
        val contentObservableByKey = when (keySection) {
            PopularBookKey.TOP_PAID_BOOK -> bookDataStoreFactory.topPaidBook(responseSize)
            PopularBookKey.TOP_FREE_BOOK -> bookDataStoreFactory.topFreeBook(responseSize)
        }
        return contentObservableByKey.map {
            val modelList: List<ChildViewModel> = it.feed.results.map { result: Result ->
                mapperBook.popularResultToBook(result)
            }.map { book: Book -> BookWrapperModel(book) }
            SectionViewModel(titleKey = keySection,
                    title = it.feed.title,
                    parentList = modelList
            )
        }
    }
}