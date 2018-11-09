package com.nimtego.itunes.presentation.information_view;

import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class InformationAlbumPresenter
        extends BasePresenter<InformationAlbumContract.View,
        BaseContract.Interactor>
        implements InformationAlbumContract.Presenter<InformationAlbumContract.View,
        BaseContract.Interactor> {

    private String id;
    private SongsRepository mSongResult;
    private List<String> songsList;

    public InformationAlbumPresenter(String id) {
        this.id = id;
        songsList = new ArrayList<>();
    }

    @Override
    public void viewIsReady() {


       /* ITunesApi iTunesApi = App.getApi();
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
                                sb.append("Country - ")
                                        .append(s.getCountry())
                                        .append("\n")
                                        .append("Release date - ")
                                        .append(s.getReleaseDate().substring(0,10));
                                view.setAlbumInformation(String.valueOf(sb));
                            }
                            else {
                                StringBuilder sb = new StringBuilder();
                                sb.append(count++)
                                        .append(". \"")
                                        .append(s.getTrackName())
                                        .append("\"");
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
        });*/
    }
}
