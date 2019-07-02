package com.nimtego.plectrum.data.entity.mapper

import android.util.Log
import com.nimtego.plectrum.data.entity.*
import com.nimtego.plectrum.data.model.itunes.*
import com.nimtego.plectrum.data.model.rss_itunes.Feed
import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.data.model.wiki.WikiSearchResult
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.SectionViewModel
import com.nimtego.plectrum.presentation.old.information_view.album.model.AlbumDetailsModel
import com.nimtego.plectrum.presentation.old.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.old.information_view.song.model.SongDetailsModel
import com.nimtego.plectrum.presentation.old.main.model.AlbumModel
import com.nimtego.plectrum.presentation.old.main.model.ArtistModel
import com.nimtego.plectrum.presentation.old.main.model.SongModel

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

    //todo
    fun tabContentModel(feed: Feed) : SectionViewModel<ChildViewModel> {
        val list = feed.results.map { tabContentResult(it) }
        return SectionViewModel(title = feed.title,
                                parentList = list)
    }

    private fun tabContentResult(result: Result) : TabChildViewModel {
        return TabChildViewModel(mainName = result.artistName,
                                 minorName = result.name,
                                 imageUrl = result.artworkUrl100,
                //todo remove this
                                 id = if(result.artistId == null) "0" else result.artistId)
    }


    fun dashBoardModel(topSongs: Feed,
                       topAlbum: Feed,
                       newMusic: Feed,
                       hotTrack: Feed): DashBoardSongsModel {

        Log.i("Presenter", topSongs.title)

        val topSongsResult = topSongs.results.map {
            Song(artistName = it.artistName,
                    artistId = it.artistId.toInt(),
                    trackName = it.name,
                    trackId = it.id.toInt(),
                    collectionId = 0,
                    trackPrice = 0.0,
                    wrapperType = it.copyright,
                    trackTimeMillis = 0,
                    trackArtWorkUrl = it.artworkUrl100)
        }

        val newMusicResult = newMusic.results.map {
            Song(artistName = it.artistName,
                    artistId = it.artistId.toInt(),
                    trackName = it.name,
                    trackId = it.id.toInt(),
                    collectionId = 0,
                    trackPrice = 0.0,
                    wrapperType = it.copyright,
                    trackTimeMillis = 0,
                    trackArtWorkUrl = it.artworkUrl100)
        }

        val hotTrackResult = hotTrack.results.map {
            Song(artistName = it.artistName,
                    artistId = it.artistId.toInt(),
                    trackName = it.name,
                    trackId = it.id.toInt(),
                    collectionId = 0,
                    trackPrice = 0.0,
                    wrapperType = it.copyright,
                    trackTimeMillis = 0,
                    trackArtWorkUrl = it.artworkUrl100)
        }

        val topAlbumsResult = topAlbum.results.map {
            Album(albumId = it.id.toInt(),
                    albumArtistId = it.artistId.toInt(),
                    albumName = it.name,
                    albumArtistName = it.artistName,
                    albumArtWorkUrl = it.artworkUrl100,
                    albumPrice = 0.0,
                    albumRealiseDate = it.releaseDate,
                    albumTrackCount = 0)
        }
        return DashBoardSongsModel(topSongs =  topSongsResult,
                              topAlbums = topAlbumsResult,
                              newMusic = newMusicResult,
                              hotTrack =  hotTrackResult)
    }

    fun topSong(topSongs: Feed) : List<Song> {
       return topSongs.results.map {
            Song(artistName = it.artistName,
                    artistId = it.artistId.toInt(),
                    trackName = it.name,
                    trackId = it.id.toInt(),
                    collectionId = 0,
                    trackPrice = 0.0,
                    wrapperType = it.copyright,
                    trackTimeMillis = 0,
                    trackArtWorkUrl = it.artworkUrl100)
        }
    }

}
