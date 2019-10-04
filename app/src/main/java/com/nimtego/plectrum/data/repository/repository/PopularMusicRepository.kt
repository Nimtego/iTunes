package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.mappers.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicKey
import com.nimtego.plectrum.domain.repository.RepositoryPopular
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
) : RepositoryPopular<BaseParentViewModel<ChildViewModel>> {

    override fun query(sectionKey: SectionsKey, responseSize: Int): Observable<BaseParentViewModel<ChildViewModel>> {
        return Observable.zip<MusicTabModel, MusicTabModel, MusicTabModel, MusicTabModel, BaseParentViewModel<ChildViewModel>>(
                newTrack(responseSize), hotTrack(responseSize), topTrack(responseSize), topAlbum(responseSize),
                Function4 { newTrack: MusicTabModel,
                            hotTrack: MusicTabModel,
                            topTrack: MusicTabModel,
                            newAlbum: MusicTabModel ->
                    BaseParentViewModel(listOf(newTrack, hotTrack, topTrack, newAlbum))
                })
    }

    private fun hotTrack(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(
                PopularMusicKey.HOT_TRACK,
                dataStoreFactory.hotTrack(responseSize)
        )
    }

    private fun newTrack(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(
                PopularMusicKey.NEW_TRACK,
                dataStoreFactory.newTrack(responseSize)
        )
    }

    private fun topTrack(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(
                PopularMusicKey.TOP_TRACK,
                dataStoreFactory.topTrack(responseSize)
        )
    }

    private fun topAlbum(responseSize: Int): Observable<MusicTabModel> {
        return transformResponse(
                PopularMusicKey.TOP_ALBUM,
                dataStoreFactory.topAlbum(responseSize)
        )
    }

    private fun transformResponse(
            titleKey: PopularMusicKey,
            response: Observable<PopularResponse>
    ): Observable<MusicTabModel> {
        return response.map {
            val songsModel = it.feed.results.map { result: Result ->
                SongWrapperModel(mapper.popularResultToMusicalModel(result))
            }
            MusicTabModel(titleKey = titleKey,
                          title = it.feed.title,
                          modelList = songsModel)
        }
    }

}