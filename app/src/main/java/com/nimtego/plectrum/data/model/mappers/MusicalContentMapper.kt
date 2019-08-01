package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import com.nimtego.plectrum.presentation.mvp.model.song.Author
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import javax.inject.Inject

class MusicalContentMapper @Inject constructor() {
    fun songResultToSong(songResult: SongResult): Song {
        return Song(artistId = songResult.artistId,
                collectionId = songResult.collectionId,
                trackId = songResult.trackId,
                artistName = songResult.artistName,
                wrapperType = songResult.wrapperType,
                trackName = songResult.trackName,
                trackPrice = songResult.trackPrice,
                trackArtWorkUrl = songResult.trackViewUrl,
                trackTimeMillis = songResult.trackTimeMillis)
    }

    fun albumResultToAlbum(result: AlbumResult): Album {
        return Album(albumId = result.collectionId,
                albumArtistId = result.artistId,
                albumName = result.collectionName,
                albumArtistName = result.artistName,
                albumTrackCount = result.trackCount,
                albumRealiseDate = result.releaseDate,
                albumPrice = result.collectionPrice,
                albumArtWorkUrl = result.artworkUrl100)
    }

    fun authorResultToArtist(result: ArtistResult): Author {
        return Author(authorId = result.artistId, authorName = result.artistName)
    }
}