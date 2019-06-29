package com.nimtego.plectrum.data.repository.datasource

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.AppNetwork
import com.nimtego.plectrum.data.network.FabricParam
import com.nimtego.plectrum.data.network.wiki.RestCountries
import com.nimtego.plectrum.data.model.itunes.AlbumsRepository
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository
import com.nimtego.plectrum.data.model.itunes.SongsRepository
import com.nimtego.plectrum.data.model.wiki.WikiSearchResult

import java.util.regex.Pattern

import io.reactivex.Observable

class CloudDataStore<E>(
        private val networkConnection: AppNetwork,
        private val cache: Cache<E>
) : DataStore {


    override fun wikiSearch(response: String): Observable<WikiSearchResult> {
        val countries = if (Pattern.matches(".*\\p{InCyrillic}.*", response))
            RestCountries.RUS
        else
            RestCountries.ENGLISH
        return networkConnection.getWikiClient(countries)
                .searchArtist(FabricParam.searchWikiInf(response))
    }

    override fun songs(response: String): Observable<SongsRepository> {
        return networkConnection.iTunesClient
                .searchSongs(FabricParam.searchSongParam(response))
    }

    override fun songsByIdAlbum(id: Int): Observable<SongsRepository> {
        return networkConnection.iTunesClient
                .getSongs(FabricParam.lookupSongsAlbum(id.toString()))
    }

    override fun artists(response: String): Observable<ArtistsRepository> {
        return networkConnection.iTunesClient
                .searchArtist(FabricParam.searchArtistParam(response))
    }

    override fun albums(response: String): Observable<AlbumsRepository> {
        return networkConnection.iTunesClient
                .searchAlbum(FabricParam.searchAlbumParam(response, 100))

    }

    override fun songById(id: Int): Observable<SongsRepository> {
        return networkConnection.iTunesClient
                .getSongs(FabricParam.lookupSongsAlbum(id.toString()))
    }

    override fun artistById(id: Int): Observable<ArtistsRepository> {
        return networkConnection.iTunesClient
                .getArtist(FabricParam.lookupArtist(id.toString()))
    }

    override fun album(response: String): Observable<AlbumsRepository> {
        return networkConnection.iTunesClient
                .getAlbum(FabricParam.lookupAlbum(response))
    }

    override fun recent(): Observable<PopularResponse> {
        return networkConnection.rssItunesAPi.recent()
    }

    override fun topSong(size: Int): Observable<PopularResponse> {
        return if (size == 0) {
            networkConnection.rssItunesAPi.topSong()
        } else {
            networkConnection.rssItunesAPi.topSongWithSize()
        }
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return networkConnection.rssItunesAPi.topAlbums()
    }

    override fun hot(): Observable<PopularResponse> {
        return networkConnection.rssItunesAPi.hotTrack()
    }

    override fun newMusic(): Observable<PopularResponse> {
        return networkConnection.rssItunesAPi.newMusic()
    }
}
