package com.nimtego.plectrum.data.rest.network.rss_itunes

import com.nimtego.plectrum.data.model.rss_itunes.Feed
import com.nimtego.plectrum.data.rest.pojo.AlbumsRepository
import com.nimtego.plectrum.data.rest.pojo.ArtistsRepository
import com.nimtego.plectrum.data.rest.pojo.SongsRepository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RssItunesApi {
    // recent realises - https://rss.itunes.apple.com/api/v1/us/itunes-music/recent-releases/all/10/explicit.json
    // new music - https://rss.itunes.apple.com/api/v1/us/itunes-music/new-music/all/10/explicit.json
    // hot track - https://rss.itunes.apple.com/api/v1/us/itunes-music/hot-tracks/all/10/explicit.json
    // top albums - https://rss.itunes.apple.com/api/v1/us/itunes-music/top-albums/all/10/explicit.json
    // top songs - https://rss.itunes.apple.com/api/v1/us/itunes-music/top-songs/all/10/explicit.json
    @GET("top-albums/all/10/explicit.json")
    fun topAlbums(): Observable<Feed>

    @GET("top-songs/all/10/explicit.json")
    fun topSong(): Observable<Feed>

    @GET("hot-tracks/all/10/explicit.json")
    fun hotTrack(): Observable<Feed>

    @GET("new-music/all/10/explicit.json")
    fun newMusic(): Observable<Feed>

    @GET("recent-releases/all/10/explicit.json")
    fun recent(): Observable<Feed>
}