package com.nimtego.itunes.domain.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.data.entity.mapper.ArtistEntityMapper;
import com.nimtego.itunes.data.rest.network.ApiConnection;
import com.nimtego.itunes.data.rest.network.FabricParam;
import com.nimtego.itunes.data.rest.network.ITunesApi;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.presentation.mvp_contracts.AlbumsCollectionContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModelManagerImp implements ModelManager {

    private final String TAG = this.getClass().getCanonicalName();

    private List<Album> albums;
    private List<Artist> artists;
    private List<Song> songs;
    private ArtistEntityMapper mapper;
    private String lastRequest = "";
    private Retrofit retrofit;

    public ModelManagerImp() {
        this.retrofit = ApiConnection.getClient();
        this.mapper = new ArtistEntityMapper(); // TODO: 17.10.2018 Dagger 
    }


    @Override
    public void getAlbums(AlbumsCollectionContract.OnFinishedListener listener, String request) {
        if (!lastRequest.equals(request)) {
            listener.onFinished(albums);
        } else {
            Log.d(TAG, request);
            ITunesApi iTunes = retrofit.create(ITunesApi.class);
            Call<AlbumsRepository> call 
                    = iTunes.searchAlbum(FabricParam.searchAlbumParam(request, 100));
            call.enqueue(new Callback<AlbumsRepository>() {
                @Override
                public void onResponse(@NonNull Call<AlbumsRepository> call, 
                                       @NonNull final Response<AlbumsRepository> response) {
                    AlbumsRepository mResultEntityList = response.body();
                    List<AlbumResult> resultEntity = mResultEntityList.getResults();
                    albums = mapper.transformAlbums(resultEntity);
                    listener.onFinished(albums);
                }

                @Override
                public void onFailure(@NonNull Call<AlbumsRepository> call, @NonNull Throwable t) {
                    listener.onFailure(t);
                }
            });
        }
    }

    @Override
    public void getArtists(AlbumsCollectionContract.OnFinishedListener listener, String request) {
        // TODO: 17.10.2018  
    }

    @Override
    public void getSongs(AlbumsCollectionContract.OnFinishedListener listener, String request) {
        // TODO: 17.10.2018
    }
}
