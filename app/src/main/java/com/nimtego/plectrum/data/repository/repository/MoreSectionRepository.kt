package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.mapper.PopularMusicMapper
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

    override fun query(request: String): Observable<ParentTabModelContainer<ChildViewModel>> {
        return when (request) {
            //todo
            "HOT_TRACK" -> getHotTrack().map { MusicTabModel(request, it) }
            "NEW_TRACK" -> getNewTrack().map { MusicTabModel(request, it) }
            "TOP_TRACK" -> getTopTrack().map { MusicTabModel(request, it) }
            "TOP_ALBUM" -> getTopAlbum().map { MusicTabModel(request, it) }
            else -> getHotTrack().map { MusicTabModel(request, it) }
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

    private fun getHotTrack(): Observable<List<SongWrapperModel>> {
        return dataStoreFactory.hotTrack().map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToSong(result)
            }.map { song: Song -> SongWrapperModel(song) }
        }
    }

    private fun getNewTrack(): Observable<List<SongWrapperModel>> {
        return dataStoreFactory.newTrack().map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToSong(result)
            }.map { song: Song -> SongWrapperModel(song) }
        }
    }

    private fun getTopTrack(): Observable<List<SongWrapperModel>> {
        return dataStoreFactory.topTrack().map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToSong(result)
            }.map { song: Song -> SongWrapperModel(song) }
        }
    }

    private fun getTopAlbum(): Observable<List<AlbumWrapperModel>> {
        return dataStoreFactory.topAlbum().map {
            it.feed.results.map { result: Result ->
                mapper.popularResultToAlbum(result)
            }.map { album: Album -> AlbumWrapperModel(album) }
        }
    }
}