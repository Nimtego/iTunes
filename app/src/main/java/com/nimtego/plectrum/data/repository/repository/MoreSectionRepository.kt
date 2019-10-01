package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.song.*
import io.reactivex.Observable

class MoreSectionRepository(
        private val dataStoreFactory: PopularMusicDataStore,
        private val mapper: PopularMusicMapper
) : Repository<ParentTabModelContainer<ChildViewModel>> {

    override fun query(request: String, responseSize: Int): Observable<ParentTabModelContainer<ChildViewModel>> {
        return when (request) {
            //todo
            "HOT_TRACK" -> getHotTrack(responseSize).map { MusicTabModel(request, it) }
            "NEW_TRACK" -> getNewTrack(responseSize).map { MusicTabModel(request, it) }
            "TOP_TRACK" -> getTopTrack(responseSize).map { MusicTabModel(request, it) }
            "TOP_ALBUM" -> getTopAlbum(responseSize).map { MusicTabModel(request, it) }
            else -> getHotTrack(responseSize).map { MusicTabModel(request, it) }
        }
//
//
//        val dataStore = this.dataStoreFactory.createCloudDataStore()
////        var songType: Observable<PopularResponse>
////        when (request) {
////            "HOT" -> songType = dataStore.hot()
////        }
//        return dataStore.topSong(2).map { mapper.topSong(it.feed) }
    }

    private fun getHotTrack(responseSize: Int): Observable<List<SongWrapperModel>> {
        return dataStoreFactory.hotTrack(responseSize).map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToMusicalModel(result)
            }.map { song: Song -> SongWrapperModel(song) }
        }
    }

    private fun getNewTrack(responseSize: Int): Observable<List<SongWrapperModel>> {
        return dataStoreFactory.newTrack(responseSize).map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToMusicalModel(result)
            }.map { song: Song -> SongWrapperModel(song) }
        }
    }

    private fun getTopTrack(responseSize: Int): Observable<List<SongWrapperModel>> {
        return dataStoreFactory.topTrack(responseSize).map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToMusicalModel(result)
            }.map { song: Song -> SongWrapperModel(song) }
        }
    }

    private fun getTopAlbum(responseSize: Int): Observable<List<AlbumWrapperModel>> {
        return dataStoreFactory.topAlbum(responseSize).map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToAlbum(result)
            }.map { album: Album -> AlbumWrapperModel(album) }
        }
    }
}