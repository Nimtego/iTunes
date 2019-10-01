package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
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

    override fun query(request: String, responseSize: Int): Observable<BaseParentViewModel<ChildViewModel>> {
        return Observable.zip<MusicTabModel, MusicTabModel, MusicTabModel, MusicTabModel, BaseParentViewModel<ChildViewModel>>(
                newTrack(responseSize), hotTrack(responseSize), topTrack(responseSize), topAlbum(responseSize),
                Function4{ newTrack: MusicTabModel,
                           hotTrack: MusicTabModel,
                           topTrack: MusicTabModel,
                           newAlbum: MusicTabModel ->
                    BaseParentViewModel(listOf(newTrack, hotTrack, topTrack, newAlbum))
                })
    }

    private fun hotTrack(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(dataStoreFactory.hotTrack(responseSize))
    }

    private fun newTrack(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(dataStoreFactory.newTrack(responseSize))
    }

    private fun topTrack(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(dataStoreFactory.topTrack(responseSize))
    }

    private fun topAlbum(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(dataStoreFactory.topAlbum(responseSize))
    }

    private fun transformResponse(
            response: Observable<PopularResponse>
    ): Observable<MusicTabModel> {
        return response.map {
            MusicTabModel(it.feed.title,
                    it.feed.results.map { result: Result ->
                        SongWrapperModel(mapper.popularResultToMusicalModel(result))
                    })
        }
    }

}