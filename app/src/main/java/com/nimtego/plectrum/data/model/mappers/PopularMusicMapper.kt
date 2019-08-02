package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import com.nimtego.plectrum.presentation.mvp.model.song.Song

class PopularMusicMapper {

    fun popularResultToSong(result: Result) : Song {
        return Song(artistName = result.artistName,
                artistId = result.artistId.toInt(),
                trackName = result.name,
                trackId = result.id.toInt(),
                collectionId = 0,
                trackPrice = 0.0,
                wrapperType = result.copyright,
                trackTimeMillis = 0,
                trackArtWorkUrl = result.artworkUrl100)
    }

    fun popularResultToAlbum(result: Result) : Album {
        return Album(albumArtistName = result.artistName,
                albumArtistId = result.artistId.toInt(),
                albumName = result.name,
                albumId = result.id.toInt(),
                albumArtWorkUrl = result.artworkUrl100,
                albumPrice = 0.0,
                albumRealiseDate = result.releaseDate,
                albumTrackCount = 0)
    }
}