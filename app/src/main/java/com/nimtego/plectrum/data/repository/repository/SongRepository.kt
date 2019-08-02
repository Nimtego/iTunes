package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.MusicalContentMapper
import com.nimtego.plectrum.data.repository.datasource.search.SongDataStoreFactory
import com.nimtego.plectrum.domain.repository.SongSource
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable
import javax.inject.Inject

class SongRepository @Inject constructor(
        private val dataStoreFactory: SongDataStoreFactory,
        private val mapper: MusicalContentMapper
) : SongSource<Song> {

    override fun getSongsByRequest(request: String): Observable<List<Song>> {
        return dataStoreFactory.getSongsByRequest(request).map {
            it.map { songResult ->
                mapper.songResultToSong(songResult)
            }
        }
    }

    override fun getSongsByAlbumId(id: Int): Observable<List<Song>> {
        return dataStoreFactory.getSongsByAlbumId(id).map {
            it.map { songResult ->
                mapper.songResultToSong(songResult)
            }
        }
    }

    override fun getSongById(id: Int): Observable<Song> {
        return dataStoreFactory.getSongById(id).map {
            mapper.songResultToSong(it)
        }
    }
}