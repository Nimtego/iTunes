package com.nimtego.plectrum.data.entity.mapper

import com.nimtego.plectrum.data.rest.pojo.*
import com.nimtego.plectrum.data.rest.pojo.wiki.WikiSearchResult
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK
import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import com.nimtego.plectrum.presentation.main.model.ArtistModelK
import com.nimtego.plectrum.presentation.main.model.SongModelK

class EntityDataMapperK {
    fun transformArtist(result: ArtistResult): ArtistModelK {
        return ArtistModelK(artistName = result.artistName,
                            primaryGenreName = result.primaryGenreName,
                            artistViewUrl = result.artistLinkUrl,
                            artistId = result.artistId.toString())
    }

    fun transformArtists(artistResultCollection: Collection<ArtistResult>): List<ArtistModelK> {
        return artistResultCollection.map { this.transformArtist(it) }
    }

    fun transformAlbum(result: AlbumResult): AlbumModelK {
        return AlbumModelK(albumName = result.collectionName,
                           albumId = result.collectionId.toString(),
                           albumArtistName = result.artistName,
                           albumArtWorkUrl = result.artworkUrl100)
    }

    private fun transformAlbums(results: Collection<AlbumResult>): List<AlbumModelK> {
        return results.asSequence()
                .filter { it.wrapperType != "artist" }
                .map { this.transformAlbum(it) }
                .toList()
    }

    fun transformAlbums(albumsRepository: AlbumsRepository): List<AlbumModelK> {
        return transformAlbums(albumsRepository.results)
    }

    private fun transformSongs(results: Collection<SongResult>): List<SongModelK> {
        return results.asSequence()
                .filter { it.wrapperType != "track" }
                .map { this.transformSong(it) }
                .toList()
    }

    private fun transformSong(songResult: SongResult): SongModelK {
        return SongModelK(trackName = songResult.trackName,
                          trackArtistName = songResult.artistName,
                          trackArtwork = songResult.artworkUrl100,
                          trackAlbumName = songResult.collectionName,
                          songId = songResult.trackId.toString())
    }

    fun transformSongs(songsRepository: SongsRepository): List<SongModelK> {
        return transformSongs(songsRepository.results)
    }

    fun transformAlbumDetail(albumResult: AlbumResult): AlbumDetailsModelK {
        return AlbumDetailsModelK(albumName = albumResult.collectionName,
                                  albumArtistName = albumResult.artistName,
                                  albumArtwork = albumResult.artworkUrl100,
                                  collectionPrice = albumResult.collectionPrice,
                                  releaseDate = albumResult.releaseDate,
                                  albumId = albumResult.collectionId)
    }

    fun transformSongDetail(songResult: SongResult): SongDetailsModelK {
        return SongDetailsModelK(songName = songResult.trackName,
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
