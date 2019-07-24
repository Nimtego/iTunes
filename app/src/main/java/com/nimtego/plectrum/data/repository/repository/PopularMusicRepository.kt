package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.mapper.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.MusicTabModel
import com.nimtego.plectrum.presentation.mvp.model.song.SongWrapperModel
import io.reactivex.Observable
import io.reactivex.functions.Function4
import javax.inject.Inject

class PopularMusicRepository @Inject constructor(
        private val dataStoreFactory: PopularMusicDataStore,
        private val mapper: PopularMusicMapper
) : Repository<BaseParentViewModel<ChildViewModel>> {

    override fun query(request: String): Observable<BaseParentViewModel<ChildViewModel>> {
        return Observable.zip<MusicTabModel, MusicTabModel, MusicTabModel, MusicTabModel, BaseParentViewModel<ChildViewModel>>(
                newTrack(), hotTrack(), topTrack(), topAlbum(),
                Function4{ newTrack: MusicTabModel,
                           hotTrack: MusicTabModel,
                           topTrack: MusicTabModel,
                           newAlbum: MusicTabModel ->
                    BaseParentViewModel(listOf(newTrack, hotTrack, topTrack, newAlbum))
                })
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

    private fun topAlbum(): Observable<MusicTabModel> {
        return dataStoreFactory.topAlbum().map {
            MusicTabModel(it.feed.title,
                    it.feed.results.map { result: Result ->
                        SongWrapperModel(mapper.popularResultToSong(result))
                    })
        }
    }
}