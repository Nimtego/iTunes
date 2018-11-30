package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.rest.network.FabricParam;
import com.nimtego.itunes.data.rest.network.NetworkConnection;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;

import io.reactivex.Observable;

public class CloudDataStore implements DataStore {

    private NetworkConnection networkConnection;
    private final Cache cache;


    public CloudDataStore(NetworkConnection networkConnection, Cache cache) {
        this.networkConnection = networkConnection;
        this.cache = cache;
    }


    @Override
    public Observable<WikiSearchResult> wikiSearch(String response) {
        return networkConnection.getWikiClient()
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
    public Observable<SongResult> song() {
        return null;
    }

    @Override
    public Observable<ArtistResult> artist() {
        return null;
    }

    @Override
    public Observable<AlbumsRepository> album(String response) {
        return networkConnection.getITunesClient()
                .getAlbum(FabricParam.lookupAlbum(response));
    }
}
