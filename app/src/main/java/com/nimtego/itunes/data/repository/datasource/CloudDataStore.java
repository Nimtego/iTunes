package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.rest.network.AppNetwork;
import com.nimtego.itunes.data.rest.network.FabricParam;
import com.nimtego.itunes.data.rest.network.wiki.RestCountries;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;

import java.util.regex.Pattern;

import io.reactivex.Observable;

public class CloudDataStore implements DataStore {

    private AppNetwork networkConnection;
    private final Cache cache;


    public CloudDataStore(AppNetwork networkConnection, Cache cache) {
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
}
