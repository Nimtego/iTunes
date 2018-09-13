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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationAlbumPresenter extends BasePresenter<InformationAlbumContract.View>
        implements InformationAlbumContract.Presenter<InformationAlbumContract.View> {

    private String id;
    private SongsRepository mSongResult;
    private List<String> songsList;

    @Override
    public Class<?> getNextActivity() {
        return null;
    }

    public InformationAlbumPresenter(String id) {
        this.id = id;
        songsList = new ArrayList<>();
    }

    @Override
    public void viewIsReady (){


        ITunesApi iTunesApi = App.getApi();
        Call<SongsRepository> call = iTunesApi.getSongs(FabricParam.lookupSongsAlbum(id));
        call.enqueue(new Callback<SongsRepository>() {
            @Override
            public void onResponse(@NonNull Call<SongsRepository> call, @NonNull final Response<SongsRepository> response) {
                mSongResult = response.body();

                view.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        int count = 1;
                        for (SongResult s :
                                mSongResult.getResults()) {
                            if (s.getWrapperType().equals("collection")){
                                view.setImageAlbum(s.getArtworkUrl100());
                                view.setAlbumName(s.getCollectionName());
                                view.setArtistName(s.getArtistName());
                                StringBuilder sb = new StringBuilder();
                                sb.append("Country - ").append(s.getCountry()).append("\n")
                                        .append("Release date - ").append(s.getReleaseDate());
                                view.setAlbumInformation(String.valueOf(sb));
                            }
                            else {
                                StringBuilder sb = new StringBuilder();
                                sb.append(count++).append(". \"").append(s.getTrackName()).append("\"");
                                songsList.add(String.valueOf(sb));
                            }
                        }
                        view.setSongList(songsList);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<SongsRepository> call, @NonNull Throwable t) {
                view.toast("An error occurred during networking");
            }
        });
    }
}
