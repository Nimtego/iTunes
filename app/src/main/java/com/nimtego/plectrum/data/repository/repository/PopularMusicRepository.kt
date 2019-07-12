package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.entity.mapper.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.mvp.model.song.AlbumWrapperModel
import com.nimtego.plectrum.presentation.mvp.model.song.MusicTabModel
import com.nimtego.plectrum.presentation.mvp.model.song.SongWrapperModel
import io.reactivex.Observable
import io.reactivex.functions.Function4

class PopularMusicRepository(
        private val dataStoreFactory: PopularMusicDataStore,
        private val mapper: PopularMusicMapper
) : Repository<List<MusicTabModel>> {

    override fun query(request: String): Observable<List<MusicTabModel>> {
        return Observable.zip<MusicTabModel, MusicTabModel, MusicTabModel, MusicTabModel, List<MusicTabModel>>(
                newTrack(), hotTrack(), topTrack(), newAlbum(),
                Function4<MusicTabModel, MusicTabModel, MusicTabModel, MusicTabModel, List<MusicTabModel>>
                { newTrack, hotTrack, topTrack, newAlbum ->
                    listOf(newTrack, hotTrack, topTrack, newAlbum)
                }
        )
    }

    private fun hotTrack(): Observable<MusicTabModel> {
        return dataStoreFactory.hotTrack().map {
            MusicTabModel(it.feed.title,
                    it.feed.results.map { result: Result ->
                        SongWrapperModel(mapper.popularResultToSong(result))
                    })
        }
    }

    private fun newTrack(): Observable<MusicTabModel> {
        return dataStoreFactory.newTrack().map {
            MusicTabModel(it.feed.title,
                    it.feed.results.map { result: Result ->
                        SongWrapperModel(mapper.popularResultToSong(result))
                    })
        }
    }

    private fun topTrack(): Observable<MusicTabModel> {
        return dataStoreFactory.topTrack().map {
            MusicTabModel(it.feed.title,
                    it.feed.results.map { result: Result ->
                        SongWrapperModel(mapper.popularResultToSong(result))
                    })
        }
    }

    private fun newAlbum(): Observable<MusicTabModel> {
        return dataStoreFactory.hotTrack().map {
            MusicTabModel(it.feed.title,
                    it.feed.results.map { result: Result ->
                        AlbumWrapperModel(mapper.popularResultToAlbum(result))
                    })
        }
    }

}