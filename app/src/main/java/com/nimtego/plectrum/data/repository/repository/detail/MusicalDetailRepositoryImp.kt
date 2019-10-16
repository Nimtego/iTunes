package com.nimtego.plectrum.data.repository.repository.detail

import com.nimtego.plectrum.data.model.mappers.MusicalDetailMapper
import com.nimtego.plectrum.data.repository.datasource.detail.DetailMusicalDataStore
import com.nimtego.plectrum.domain.repository.detail.MusicalDetailRepository
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import io.reactivex.Observable
import javax.inject.Inject
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.functions.BiFunction


class MusicalDetailRepositoryImp @Inject constructor(
        private val dataStoreFactory: DetailMusicalDataStore,
        private val mapper: MusicalDetailMapper
) : MusicalDetailRepository {

    override fun getSongById(id: String): Observable<SongDetailModel> {
        return this.dataStoreFactory.songById(id).map {
            this.mapper.songResultToModel(filterTrack(it).first())
        }
    }

    override fun getAlbumById(id: String): Observable<AlbumDetailModel> {
        return Observable.zip(dataStoreFactory.albumById(id),
                              dataStoreFactory.songsByAlbumId(id),
                              BiFunction {album,
                                          songs
                                          -> this.mapper.albumResultToModel(album, filterTrack(songs))
                              })
    }

    private fun filterTrack(songsResult: Collection<SongResult>): List<SongResult> {
        return songsResult.filter { it.wrapperType == validSongWrapperType }
    }

    override fun getArtistById(id: String): Observable<ArtistDetailModel> {
        return this.dataStoreFactory.artistById(id).flatMap { artistResult ->
            this.dataStoreFactory.albumsByArtistId(artistResult.artistId.toString())
                    .map { albumResult ->
                        this.mapper.artistResultToModel(artistResult, albumResult)
                    }
        }
    }

    companion object {
        const val validSongWrapperType = "track"
    }
}