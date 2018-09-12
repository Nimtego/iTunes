package com.nimtego.itunes.presenter;

import android.support.annotation.NonNull;

import com.nimtego.itunes.App;
import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.InformationAlbumContract;
import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.FabricParam;
import com.nimtego.itunes.service.ITunesApi;
import com.nimtego.itunes.service.SongResult;
import com.nimtego.itunes.service.SongsRepository;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationAlbumPresenter extends BasePresenter<InformationAlbumContract.View>
        implements InformationAlbumContract.Presenter<InformationAlbumContract.View> {

    private int id;
    private SongResult mSongResult;

    @Override
    public Class<?> getNextActivity() {
        return null;
    }

    public InformationAlbumPresenter(int id) {
        this.id = id;
    }

    @Override
    public void viewIsReady (){


        ITunesApi iTunesApi = App.getApi();
        Call<SongResult> call = iTunesApi.getSongs(FabricParam.searchSongsAlbum(String.valueOf(id)));
        call.enqueue(new Callback<SongResult>() {
            @Override
            public void onResponse(@NonNull Call<SongResult> call, @NonNull final Response<SongResult> response) {
                mSongResult = response.body();
                view.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setImageAlbum(mSongResult.getArtworkUrl100());
                        view.setAlbumName(mSongResult.getCollectionArtistName());
                        view.setArtistName(mSongResult.getArtistName());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<SongResult> call, @NonNull Throwable t) {
                view.toast("An error occurred during networking");
            }
        });
    }
}
