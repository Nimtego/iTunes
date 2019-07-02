package com.nimtego.plectrum.data.network.rss_itunes

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RssItunesApi {

// Mark: Music
    // recent realises - https://rss.itunes.apple.com/api/v1/us/itunes-music/recent-releases/all/10/explicit.json
    // new music - https://rss.itunes.apple.com/api/v1/us/itunes-music/new-music/all/10/explicit.json
    // hot track - https://rss.itunes.apple.com/api/v1/us/itunes-music/hot-tracks/all/10/explicit.json
    // top albums - https://rss.itunes.apple.com/api/v1/us/itunes-music/top-albums/all/10/explicit.json
    // top songs - https://rss.itunes.apple.com/api/v1/us/itunes-music/top-songs/all/10/explicit.json

//Mark: Movie
    // top movie https://rss.itunes.apple.com/api/v1/us/movies/top-movies/all/10/explicit.json

//Mark: Book
    // top free https://rss.itunes.apple.com/api/v1/us/books/top-free/all/10/explicit.json
    // top paid https://rss.itunes.apple.com/api/v1/us/books/top-paid/all/10/explicit.json

    @GET("itunes-music/top-albums/all/10/explicit.json")
    fun topAlbums(): Observable<PopularResponse>

    @GET("itunes-music/top-songs/all/10/explicit.json")
    fun topSong(): Observable<PopularResponse>

    @GET("itunes-music/top-songs/all/50/explicit.json")
    fun topSongWithSize(): Observable<PopularResponse>

    @GET("itunes-music/hot-tracks/all/10/explicit.json")
    fun hotTrack(): Observable<PopularResponse>

    @GET("itunes-music/new-music/all/10/explicit.json")
    fun newMusic(): Observable<PopularResponse>

    @GET("itunes-music/recent-releases/all/10/explicit.json")
    fun recent(): Observable<PopularResponse>

    @GET("movies/top-movies/all/10/explicit.json")
    fun topMovie(): Observable<PopularResponse>

    @GET("books/top-free/all/10/explicit.json")
    fun topFreeBooks(): Observable<PopularResponse>

    @GET("books/top-paid/all/10/explicit.json")
    fun topPaidBooks(): Observable<PopularResponse>

}