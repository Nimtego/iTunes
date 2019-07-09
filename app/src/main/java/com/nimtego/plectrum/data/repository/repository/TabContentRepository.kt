package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.TabContentModel
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.DataStore
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory
import com.nimtego.plectrum.domain.repository.RepositoryK
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function4

class TabContentRepository(
        private val dataStoreFactory: DataStoreFactory<PopularResponse>,
        private val mapper: EntityDataMapper
) : RepositoryK<TabContentModel> {

    private fun musicQuery() : Observable<TabContentModel> {
        val dataStore = dataStore()
        val hotOb = dataStore.hot()
        val newMusicOb = dataStore.newMusic()
        val topAlbumOb = dataStore.topAlbum()
        val topSongOb = dataStore.topSong(0)
        return Observable.zip<PopularResponse, PopularResponse, PopularResponse, PopularResponse, TabContentModel>(topAlbumOb,
                topSongOb,
                hotOb,
                newMusicOb,
                Function4<PopularResponse, PopularResponse, PopularResponse, PopularResponse, TabContentModel>
                { topAlbum, topSong, hotSong, newMusic ->
                    TabContentModel(listOf(mapper.tabContentModel(topSong.feed),
                                           mapper.tabContentModel(topAlbum.feed),
                                           mapper.tabContentModel(newMusic.feed),
                                           mapper.tabContentModel(hotSong.feed)))
                }
        )
    }

    private fun movieQuery() : Observable<TabContentModel> {
        val dataStore = dataStore()
        return dataStore.topMovie().map { TabContentModel(listOf(mapper.tabContentModel(it.feed))) }
    }

    private fun bookQuery() : Observable<TabContentModel> {
        val dataStore = dataStore()
        val topFreeBook = dataStore.topFreeBooks()
        val topPaidBook = dataStore.topPaidBooks()
        return Observable.zip<PopularResponse, PopularResponse, TabContentModel>(
                topFreeBook,
                topPaidBook,
                BiFunction<PopularResponse, PopularResponse, TabContentModel>
                { freeBook, paidBook ->
                    TabContentModel(listOf(mapper.tabContentModel(freeBook.feed),
                                    mapper.tabContentModel(paidBook.feed)))
                }
        )
    }


    private fun dataStore() : DataStore {
        return this.dataStoreFactory.createCloudDataStore()
    }

    override fun query(request: String): Observable<TabContentModel> {
//        val MUSIC_TAB = "music_tab"
//        val MOVIE_TAB = "movie_tab"
//        val BOOK_TAB = "book_tab"
        //todo remove hardcode
        return when (request) {
            "MUSIC_TAB" -> musicQuery()
            "MOVIE_TAB" -> movieQuery()
            "BOOK_TAB" -> bookQuery()
            else -> Observable.create { PopularResponse() }
        }
    }
}