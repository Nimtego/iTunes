package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.domain.repository.Repository
import io.reactivex.Observable

class PopularMusicRepository(
        private val dataStoreFactory: PopularMusicDataStore,
        private val mapper: EntityDataMapper
) : Repository<Ta> {

    override fun hotTrack(): Observable<List<Song>> {
        return dataStoreFactory.hotTrack().map {
            mapper.topSong(it.feed)
        }
    }

    override fun newTrack(): Observable<List<Song>> {
        return dataStoreFactory.newTrack().map {
            mapper.topSong(it.feed)
        }
    }

    override fun topTrack(): Observable<List<Song>> {
        return dataStoreFactory.topTrack().map {
            mapper.topSong(it.feed)
        }
    }

    override fun newAlbum(): Observable<List<Album>> {
        return dataStoreFactory.hotTrack().map {
            mapper.topAlbum(it.feed)
        }
    }

}