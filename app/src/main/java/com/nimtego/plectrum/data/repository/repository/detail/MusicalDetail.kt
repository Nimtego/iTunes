package com.nimtego.plectrum.data.repository.repository.detail

import com.nimtego.plectrum.data.model.mappers.MusicalDetailMapper
import com.nimtego.plectrum.data.repository.datasource.detail.DetailMusicalDataStore
import com.nimtego.plectrum.domain.repository.detail.MusicalDetailRepository
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import com.nimtego.plectrum.data.model.itunes.AlbumResult
import android.R.id
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.functions.BiFunction


class MusicalDetail @Inject constructor(
        private val dataStoreFactory: DetailMusicalDataStore,
        private val mapper: MusicalDetailMapper
) : MusicalDetailRepository {

    override fun getSongById(id: String): Observable<SongModel> {
        return this.dataStoreFactory.songById(id).map {
            this.mapper.songResultToModel(it)
        }
    }

    //todo remove firs songs item !!!!
    override fun getAlbumById(id: String): Observable<AlbumModel> {
        return Observable.zip(dataStoreFactory.albumById(id),
                              dataStoreFactory.songsByAlbumId(id),
                              BiFunction {album,
                                          songs
                                          -> this.mapper.albumResultToModel(album,
                                      songs.takeLast(5))
                              })
    }

    override fun getArtistById(id: String): Observable<ArtistModel> {
        return this.dataStoreFactory.artistById(id).flatMap { artistResult ->
            this.dataStoreFactory.albumsByArtistId(artistResult.artistId.toString())
                    .map { albumResult ->
                        this.mapper.artistResultToModel(artistResult, albumResult)
                    }
        }
    }
}