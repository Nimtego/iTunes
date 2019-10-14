package com.nimtego.plectrum.data.network.itunes

object ItunesFabricParam {

    private const val limitOfItems = 200

    fun searchAlbum(album: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?term=metallica&entity=album&limit=5&attribute=albumTerm
         */
        return hashMapOf("term" to album,
                         "entity" to "album",
                         "limit" to limit.toString(),
                         "attribute" to "albumTerm")
    }

    //https://itunes.apple.com/lookup?id=579372950&entity=song
    fun lookupSongsById(id: String, limit: Int = limitOfItems): Map<String, String> {
        return hashMapOf("id" to id,
                         "entity" to "song",
                         "limit" to limit.toString())
    }

    //https://itunes.apple.com/lookup?id=579372950&entity=album
    fun lookupAlbumById(id: String, limit: Int = limitOfItems): Map<String, String> {
        return hashMapOf("id" to id,
                         "entity" to "song",
                         "limit" to limit.toString())
    }

    fun searchSong(song: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?media=music&entity=musicTrack&limit=5&attribute=songTerm&term=xxx
         */
        return hashMapOf("term" to song,
                         "entity" to "musicTrack",
                         "limit" to limit.toString(),
                         "attribute" to "songTerm")
    }

    fun searchArtist(artist: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?media=music&entity=musicArtist&limit=5&attribute=artistTerm&term=xxxx
         */
        return hashMapOf("media" to "music",
                         "entity" to "musicArtist",
                         "attribute" to "artistTerm",
                         "term" to artist,
                         "limit" to limit.toString())
    }

    fun searchWikiInf(response: String): Map<String, String> {
        /*
        https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=XXXX
         */

        /*
        https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=XXX
         */
        return hashMapOf("format" to "json",
                         "action" to "query",
                         "list" to "search",
                         "srsearch" to response)
    }

    fun lookupArtist(id: String): Map<String, String> {
        return hashMapOf("id" to id,
                         "entity" to "musicArtist")
    }

    fun searchMovie(request: String, limit: Int = limitOfItems): Map<String, String> {
        /*
         https://itunes.apple.com/search?term=XXXXXX&entity=movie&limit=10&attribute=movieTerm
         */
        return hashMapOf("entity" to "movie",
                         "attribute" to "movieTerm",
                         "term" to request,
                         "limit" to limit.toString())
    }

    //https://itunes.apple.com/lookup?id=909253&entity=movie
    fun lookupMovie(id: Int): Map<String, String> {
        return hashMapOf("id" to id.toString(),
                "entity" to "movie")
    }
}
