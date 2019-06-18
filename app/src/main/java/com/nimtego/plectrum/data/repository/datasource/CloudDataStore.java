package com.nimtego.plectrum.data.repository.datasource;

import com.nimtego.plectrum.data.cache.Cache;
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse;
import com.nimtego.plectrum.data.network.AppNetwork;
import com.nimtego.plectrum.data.network.FabricParam;
import com.nimtego.plectrum.data.network.wiki.RestCountries;
import com.nimtego.plectrum.data.model.itunes.AlbumsRepository;
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository;
import com.nimtego.plectrum.data.model.itunes.SongsRepository;
import com.nimtego.plectrum.data.model.wiki.WikiSearchResult;

import java.util.regex.Pattern;

import io.reactivex.Observable;

public class CloudDataStore<E> implements DataStore {

    private AppNetwork networkConnection;
    private final Cache<E> cache;


    public CloudDataStore(AppNetwork networkConnection, Cache<E> cache) {
        this.networkConnection = networkConnection;
        this.cache = cache;
    }


    @Override
    public Observable<WikiSearchResult> wikiSearch(String response) {
        RestCountries countries = Pattern.matches(".*\\p{InCyrillic}.*", response) ?
                RestCountries.RUS : RestCountries.ENGLISH;
        return networkConnection.getWikiClient(countries)
                .searchArtist(FabricParam.searchWikiInf(response));
    }

    @Override
    public Observable<SongsRepository> songs(String request) {
        return networkConnection.getITunesClient()
                .searchSongs(FabricParam.searchSongParam(request));
    }

    @Override
    public Observable<SongsRepository> songsByIdAlbum(int id) {
        return networkConnection.getITunesClient()
                .getSongs(FabricParam.lookupSongsAlbum(String.valueOf(id)));
    }

    @Override
    public Observable<ArtistsRepository> artists(String request) {
        return networkConnection.getITunesClient()
                .searchArtist(FabricParam.searchArtistParam(request));
    }

    @Override
    public Observable<AlbumsRepository> albums(String request) {
        return networkConnection.getITunesClient()
                .searchAlbum(FabricParam.searchAlbumParam(request, 100));

    }

    @Override
    public Observable<SongsRepository> songById(int id) {
        return networkConnection.getITunesClient()
                .getSongs(FabricParam.lookupSongsAlbum(String.valueOf(id)));
    }

    @Override
    public Observable<ArtistsRepository> artistById(int id) {
        return networkConnection.getITunesClient()
                .getArtist(FabricParam.lookupArtist(String.valueOf(id)));
    }

    @Override
    public Observable<AlbumsRepository> album(String response) {
        return networkConnection.getITunesClient()
                .getAlbum(FabricParam.lookupAlbum(response));
    }

    @Override
    public Observable<PopularResponse> recent() {
        return networkConnection.getRssItunesAPi().recent();
    }

    @Override
    public Observable<PopularResponse> topSong() {
        return networkConnection.getRssItunesAPi().topSong();
    }

    @Override
    public Observable<PopularResponse> topAlbum() {
        return networkConnection.getRssItunesAPi().topAlbums();
    }

    @Override
    public Observable<PopularResponse> hot() {
        return networkConnection.getRssItunesAPi().hotTrack();
    }

    @Override
    public Observable<PopularResponse> newMusick() {
        return networkConnection.getRssItunesAPi().newMusic();
    }
}
