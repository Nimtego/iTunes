package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel

class MusicalDetailMapper {
    fun songResultToModel(songResult: SongResult): SongModel {
        return SongModel(
                trackName = songResult.trackName,
                trackAlbumName = songResult.collectionName,
                trackArtistName = songResult.artistName,
                trackArtwork = songResult.artworkUrl100,
                trackId = songResult.trackId.toString(),
                trackPrice = songResult.trackPrice.toString())
    }

    fun albumResultToModel(albumResult: AlbumResult, songsAlbum: List<SongResult>): AlbumModel {
        return AlbumModel(
                albumName = albumResult.collectionName,
                albumArtistName = albumResult.artistName,
                albumArtwork = albumResult.artworkUrl100,
                albumId = albumResult.collectionId.toString(),
                albumPrice = albumResult.collectionPrice.toString(),
                albumReleaseDate = albumResult.releaseDate,
                albumSongs = songsAlbum.map { songResultToModel(it) }
        )
    }

    private fun albumResultToModel(albumResult: AlbumResult): AlbumModel {
        return albumResultToModel(albumResult, emptyList())
    }

    fun artistResultToModel(artistResult: ArtistResult,
                            albumResult: List<AlbumResult>): ArtistModel {
        return ArtistModel(
                artistName = artistResult.artistName,
                artistArtwork = artistResult.artistLinkUrl,
                artistId = artistResult.artistId.toString(),
                primaryGenreName = artistResult.primaryGenreName,
                artistViewUrl = artistResult.artistLinkUrl,
                artistAlbums = albumResult.map { albumResultToModel(it) })
    }
}