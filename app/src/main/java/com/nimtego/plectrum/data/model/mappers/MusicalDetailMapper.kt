package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel

class MusicalDetailMapper {
    fun songResultToModel(songResult: SongResult): SongDetailModel {
        return SongDetailModel(
                trackName = songResult.trackName,
                trackAlbumName = songResult.collectionName,
                trackArtistName = songResult.artistName,
                trackArtwork = songResult.artworkUrl100,
                trackId = songResult.trackId.toString(),
                trackPrice = songResult.trackPrice.toString())
    }

    fun albumResultToModel(albumResult: AlbumResult, songsAlbum: List<SongResult>): AlbumDetailModel {
        return AlbumDetailModel(
                albumName = albumResult.collectionName,
                albumArtistName = albumResult.artistName,
                albumArtwork = albumResult.artworkUrl100,
                albumId = albumResult.collectionId.toString(),
                albumPrice = albumResult.collectionPrice.toString(),
                albumReleaseDate = albumResult.releaseDate,
                albumSongDetails = songsAlbum.map { songResultToModel(it) }
        )
    }

    private fun albumResultToModel(albumResult: AlbumResult): AlbumDetailModel {
        return albumResultToModel(albumResult, emptyList())
    }

    fun artistResultToModel(artistResult: ArtistResult,
                            albumResult: List<AlbumResult>): ArtistDetailModel {
        return ArtistDetailModel(
                artistName = artistResult.artistName,
                artistArtwork = artistResult.artistLinkUrl,
                artistId = artistResult.artistId.toString(),
                primaryGenreName = artistResult.primaryGenreName,
                artistViewUrl = artistResult.artistLinkUrl,
                artistAlbumDetails = albumResult.map { albumResultToModel(it) })
    }
}