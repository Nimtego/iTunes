package com.nimtego.itunes.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.service.Controller;
import com.nimtego.itunes.service.ITunesApi;
import com.nimtego.itunes.service.ResultEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nimtego.itunes.utils.AppConstants.EMPTY_FIELD;

public class AlbumsCollectionPresenter
        extends BasePresenter<AlbumsCollectionContract.View>
        implements AlbumsCollectionContract.Presenter<AlbumsCollectionContract.View> {

    private List<ResultEntity> mResultEntityList;

    @Override
    public void search() {
        String message = view.getsearchText();
/*        if (message.isEmpty()) {*/
            view.toast(message);
/*        }*/
        ITunesApi iTunesApi = Controller.getApi();
        iTunesApi.getData(message, 50).enqueue(new Callback<List<ResultEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<ResultEntity>> call, @NonNull Response<List<ResultEntity>> response) {
                mResultEntityList.addAll(response.body());
                view.setSearchList(mResultEntityList);
            }

            @Override
            public void onFailure(@NonNull Call<List<ResultEntity>> call, @NonNull Throwable t) {
                view.toast("An error occurred during networking");
            }
        });
        view.setSearchList(mResultEntityList);
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
