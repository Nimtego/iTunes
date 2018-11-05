package com.nimtego.itunes.data.repository.datasource;

import android.support.annotation.NonNull;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.rest.network.FabricParam;
import com.nimtego.itunes.data.rest.network.ITunesApi;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloudDataStore implements DataStore{

    private final ITunesApi restApi;
    private final Cache cache;


    public CloudDataStore(ITunesApi restApi, Cache cache) {
        this.restApi = restApi;
        this.cache = cache;
    }

    @Override
    public Observable<SongsRepository> songs() {
        return null;
    }

    @Override
    public Observable<ArtistsRepository> artists() {
        return null;
    }

    @Override
    public Observable<AlbumsRepository> albums(String request) {
        return restApi.searchAlbum(FabricParam.searchAlbumParam(request, 100));



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
    public Observable<AlbumResult> album() {
        return null;
    }
}
