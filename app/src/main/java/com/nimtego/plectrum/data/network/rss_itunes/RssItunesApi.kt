package com.nimtego.plectrum.data.network.rss_itunes

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RssItunesApi {
    // recent realises - https://rss.itunes.apple.com/api/v1/us/itunes-music/recent-releases/all/10/explicit.json
    // new music - https://rss.itunes.apple.com/api/v1/us/itunes-music/new-music/all/10/explicit.json
    // hot track - https://rss.itunes.apple.com/api/v1/us/itunes-music/hot-tracks/all/10/explicit.json
    // top albums - https://rss.itunes.apple.com/api/v1/us/itunes-music/top-albums/all/10/explicit.json
    // top songs - https://rss.itunes.apple.com/api/v1/us/itunes-music/top-songs/all/10/explicit.json
    @GET("top-albums/all/10/explicit.json")
    fun topAlbums(): Observable<PopularResponse>

    @GET("top-songs/all/10/explicit.json")
    fun topSong(): Observable<PopularResponse>

    @GET("hot-tracks/all/10/explicit.json")
    fun hotTrack(): Observable<PopularResponse>

    @GET("new-music/all/10/explicit.json")
    fun newMusic(): Observable<PopularResponse>

    @GET("recent-releases/all/10/explicit.json")
    fun recent(): Observable<PopularResponse>
}