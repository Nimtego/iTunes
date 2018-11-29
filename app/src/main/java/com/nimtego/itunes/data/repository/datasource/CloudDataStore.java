package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.rest.network.FabricParam;
import com.nimtego.itunes.data.rest.network.ITunesApi;
import com.nimtego.itunes.data.rest.network.WikiApi;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class CloudDataStore implements DataStore {

    private final ITunesApi iTunesApi;
    private final WikiApi wikiApi;
    private final Cache cache;


    public CloudDataStore(ITunesApi restApi, WikiApi wikiApi, Cache cache) {
        this.iTunesApi = restApi;
        this.wikiApi = wikiApi;
        this.cache = cache;
    }


    @Override
    public Observable<WikiSearchResult> wikiSearch(String response) {
        return wikiApi.searchArtist(FabricParam.searchWikiInf(response));
    }

    @Override
    public Observable<SongsRepository> songs(String request) {
        return iTunesApi.searchSongs(FabricParam.searchSongParam(request));
    }

    @Override
    public Observable<SongsRepository> songsByIdAlbum(int id) {
        return iTunesApi.getSongs(FabricParam.lookupSongsAlbum(String.valueOf(id)));
    }

    @Override
    public Observable<ArtistsRepository> artists(String request) {
        return iTunesApi.searchArtist(FabricParam.searchArtistParam(request));
    }

    @Override
    public Observable<AlbumsRepository> albums(String request) {
        return iTunesApi.searchAlbum(FabricParam.searchAlbumParam(request, 100));



/*        Observable<AlbumsRepository> observable =
                Observable.fromCallable(() -> restApi.searchAlbum(FabricParam.searchAlbumParam(request, 100)).enqueue(new Callback<AlbumsRepository>() {
                    @Override
                    public void onResponse(@NonNull Call<AlbumsRepository> call, @NonNull Response<AlbumsRepository> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<AlbumsRepository> call, Throwable t) {

                    }
                }));*/


/*        restApi.searchAlbum(FabricParam.searchAlbumParam(request, 100)).enqueue(new Callback<AlbumsRepository>() {
            @Override
            public void onResponse(Call<AlbumsRepository> call, Response<AlbumsRepository> response) {

            }

            @Override
            public void onFailure(Call<AlbumsRepository> call, Throwable t) {

            }
        });*/


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
        return iTunesApi.getAlbum(FabricParam.lookupAlbum(response));
    }
}
