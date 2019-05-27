package com.nimtego.plectrum.data.entity.mapper

import com.nimtego.plectrum.data.rest.pojo.*
import com.nimtego.plectrum.data.rest.pojo.wiki.WikiSearchResult
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel
import com.nimtego.plectrum.presentation.main.model.AlbumModel
import com.nimtego.plectrum.presentation.main.model.ArtistModel
import com.nimtego.plectrum.presentation.main.model.SongModel

class EntityDataMapper {
    fun transformArtist(result: ArtistResult): ArtistModel {
        return ArtistModel(artistName = result.artistName,
                           primaryGenreName = result.primaryGenreName,
                            artistViewUrl = result.artistLinkUrl,
                            artistId = result.artistId.toString(),
                            artistArtwork = result.artistLinkUrl)
    }

    fun transformArtists(artistResultCollection: Collection<ArtistResult>): List<ArtistModel> {
        return artistResultCollection.map { this.transformArtist(it) }
    }

    fun transformAlbum(result: AlbumResult): AlbumModel {
        return AlbumModel(albumName = result.collectionName,
                           albumId = result.collectionId.toString(),
                           albumArtistName = result.artistName,
                           albumArtWorkUrl = result.artworkUrl100,
                           albumArtwork = result.artworkUrl100)
    }

    private fun transformAlbums(results: Collection<AlbumResult>): List<AlbumModel> {
        return results.asSequence()
                .filter { it.wrapperType != "artist" }
                .map { this.transformAlbum(it) }
                .toList()
    }

    fun transformAlbums(albumsRepository: AlbumsRepository): List<AlbumModel> {
        return transformAlbums(albumsRepository.results)
    }

    private fun transformSongs(results: Collection<SongResult>): List<SongModel> {
        return results.asSequence()
                .filter { it.wrapperType != "track" }
                .map { this.transformSong(it) }
                .toList()
    }

    private fun transformSong(songResult: SongResult): SongModel {
        return SongModel(trackName = songResult.trackName,
                          trackArtistName = songResult.artistName,
                          trackArtwork = songResult.artworkUrl100,
                          trackAlbumName = songResult.collectionName,
                          songId = songResult.trackId.toString())
    }

    fun transformSongs(songsRepository: SongsRepository): List<SongModel> {
        return transformSongs(songsRepository.results)
    }

    fun transformAlbumDetail(albumResult: AlbumResult): AlbumDetailsModel {
        return AlbumDetailsModel(albumName = albumResult.collectionName,
                                  albumArtistName = albumResult.artistName,
                                  albumArtwork = albumResult.artworkUrl100,
                                  collectionPrice = albumResult.collectionPrice,
                                  releaseDate = albumResult.releaseDate,
                                  albumId = albumResult.collectionId)
    }

    fun transformSongDetail(songResult: SongResult): SongDetailsModel {
        return SongDetailsModel(songName = songResult.trackName,
                                 songArtwork = songResult.artworkUrl100,
                                 songPrice = songResult.trackPrice,
                                 songArtistName = songResult.artistName,
                                 songAlbumName = songResult.collectionName,
                                 releaseDate = songResult.releaseDate)
    }

    fun wikiInformationArtist(wiki: WikiSearchResult): String {
        return wiki.query
                .search[0]
                .snippet
                .replace("\\<.*?\\>".toRegex(), " ")
                .replace("&quot;", "")
                .trim { it <= ' ' }
    }

    fun transformArtistDetail(artistResult: ArtistResult): ArtistDetailsModelK {
        return ArtistDetailsModelK(artistArtwork = artistResult.artistLinkUrl,
                                   artistId = artistResult.artistId,
                                   artistName = artistResult.artistName)
    }

}
