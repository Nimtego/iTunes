package com.nimtego.plectrum.data.network

import java.util.HashMap

object FabricParam {

    private const val limitOfItems = 200

    fun searchAlbumParam(album: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?term=metallica&entity=album&limit=5&attribute=albumTerm
         */
        val param = HashMap<String, String>()
        param["term"] = album
        param["entity"] = "album"
        param["limit"] = limit.toString()
        param["attribute"] = "albumTerm"
        return param
    }

    //https://itunes.apple.com/lookup?id=579372950&entity=song
    fun lookupSongsByIdAlbum(id: Int, limit: Int = limitOfItems): Map<String, String> {
        val param = HashMap<String, String>()
        param["id"] = id.toString()
        param["entity"] = "song"
        param["limit"] = limit.toString()
        return param
    }

    //https://itunes.apple.com/lookup?id=579372950&entity=album
    fun lookupAlbum(album: String, limit: Int = limitOfItems): Map<String, String> {
        val param = HashMap<String, String>()
        param["id"] = album
        param["entity"] = "song"
        param["limit"] = limit.toString()
        return param
    }

    fun searchSongParam(song: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?media=music&entity=musicTrack&limit=5&attribute=songTerm&term=xxx
         */
        val param = HashMap<String, String>()
        param["term"] = song
        param["entity"] = "musicTrack"
        param["limit"] = limit.toString()
        param["attribute"] = "songTerm"
        return param
    }

    fun searchArtistParam(artist: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?media=music&entity=musicArtist&limit=5&attribute=artistTerm&term=xxxx
         */
        val param = HashMap<String, String>()
        param["media"] = "music"
        param["entity"] = "musicArtist"
        param["attribute"] = "artistTerm"
        param["term"] = artist
        param["limit"] = limit.toString()
        return param
    }

    fun searchWikiInf(response: String): Map<String, String> {
        /*
        https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=XXXX
         */

        /*
        https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=XXX
         */
        val param = HashMap<String, String>()
        param["format"] = "json"
        param["action"] = "query"
        param["list"] = "search"
        param["srsearch"] = response
        return param

    }

    fun lookupArtist(id: Int): Map<String, String> {
        val param = HashMap<String, String>()
        param["id"] = id.toString()
        param["entity"] = "musicArtist"
        return param
    }
}
