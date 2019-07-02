package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.data.entity.DashBoardSongsModel
import com.nimtego.plectrum.presentation.mvp.view_model.information_view.AlbumDetailsModel
import com.nimtego.plectrum.presentation.mvp.view_model.information_view.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.mvp.view_model.information_view.SongDetailsModel
import com.nimtego.plectrum.presentation.mvp.view_model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.view_model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.view_model.music.SongModel

import io.reactivex.Observable

@Deprecated("<ISP. Remove when stops being useful>")
interface Repository {
    fun songs(request: String): Observable<List<SongModel>>

    fun artists(request: String): Observable<List<ArtistModel>>

    fun albums(request: String): Observable<List<AlbumModel>>

    fun songDeteil(name: String): Observable<SongDetailsModel>

    fun artist(name: String): Observable<ArtistModel>

    fun albumDetail(name: String): Observable<AlbumDetailsModel>

    fun artistDetail(name: String): Observable<ArtistDetailsModelK>

    fun dashBoardModel(): Observable<DashBoardSongsModel>
}
