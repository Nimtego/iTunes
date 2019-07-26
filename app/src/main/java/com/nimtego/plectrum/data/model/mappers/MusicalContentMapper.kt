package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.presentation.mvp.model.song.Song

class MusicalContentMapper {
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
}