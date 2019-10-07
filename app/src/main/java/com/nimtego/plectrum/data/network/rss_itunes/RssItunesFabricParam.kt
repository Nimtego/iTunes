package com.nimtego.plectrum.data.network.rss_itunes

import java.lang.StringBuilder

object RssItunesFabricParam {

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


    private const val endOfUrlRequest = "/explicit.json"
    private const val limitOfItems = 200

    private const val music = "itunes-music/"
    private const val newMusic = "new-music/all/"
    private const val hotTracks = "hot-tracks/all/"
    private const val topAlbums = "top-albums/all/"
    private const val topSongs = "top-songs/all/"

    private const val movie = "movies/"
    private const val topMovies = "top-movies/all/"

    private const val book = "books/"
    private const val topFree = "top-free/all/"
    private const val topPaid = "top-paid/all/"

    private fun getValidSize(size: Int) =
            if(size > limitOfItems) limitOfItems else size

    fun topAlbum(responseSize: Int): String {
        return StringBuilder(music)
                .append(topAlbums)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }

    fun newMusic(responseSize: Int): String {
        return StringBuilder(music)
                .append(newMusic)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }

    fun hotTracks(responseSize: Int): String {
        return StringBuilder(music)
                .append(hotTracks)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }

    fun topSongs(responseSize: Int): String {
        return StringBuilder(music)
                .append(topSongs)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }

    fun topMovies(responseSize: Int): String {
        return StringBuilder(movie)
                .append(topMovies)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }

    fun topFreeBook(responseSize: Int): String {
        return StringBuilder(book)
                .append(topPaid)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }

    fun topPaidBook(responseSize: Int): String {
        return StringBuilder(book)
                .append(topFree)
                .append(getValidSize(responseSize))
                .append(endOfUrlRequest)
                .toString()
    }
}
