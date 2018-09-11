package com.nimtego.itunes.presenter;

import android.support.annotation.NonNull;

import com.nimtego.itunes.App;
import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.ITunesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsCollectionPresenter
        extends BasePresenter<AlbumsCollectionContract.View>
        implements AlbumsCollectionContract.Presenter<AlbumsCollectionContract.View> {

    private EntityRepository mResultEntityList;

    @Override
    public void search() {
        String message = view.getsearchText();
/*        if (message.isEmpty()) {*/
            view.toast(message);
/*        }*/
        ITunesApi iTunesApi = App.getApi();
        iTunesApi.getData(message, 50).enqueue(new Callback<EntityRepository>() {
            @Override
            public void onResponse(Call<EntityRepository> call, Response<EntityRepository> response) {
                    mResultEntityList = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<EntityRepository> call, @NonNull Throwable t) {
                view.toast("An error occurred during networking");
            }
        });
        try {
            view.setSearchList(mResultEntityList.getResults());
        } catch (NullPointerException e) {
            view.toast("NPE\n" +e.getMessage());
        }
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void pushInRV(int position) {

    }

    @Override
    public void longPushInRV(int position) {

    }

    @Override
    public Class<?> getNextActivity() {
        return null;
    }
}
