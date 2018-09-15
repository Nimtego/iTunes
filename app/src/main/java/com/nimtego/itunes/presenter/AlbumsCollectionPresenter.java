package com.nimtego.itunes.presenter;

import android.support.annotation.NonNull;

import com.nimtego.itunes.App;
import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.FabricParam;
import com.nimtego.itunes.service.ITunesApi;
import com.nimtego.itunes.service.ResultEntity;
import com.nimtego.itunes.utils.IpTags;
import com.nimtego.itunes.view.InformationAlbumActivity;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        view.toast(message);
        ITunesApi iTunesApi = App.getApi();
        Call<EntityRepository> call = iTunesApi.getAlbum(FabricParam.searchAlbumParam(message));
        call.enqueue(new Callback<EntityRepository>() {
            @Override
            public void onResponse(@NonNull Call<EntityRepository> call, @NonNull final Response<EntityRepository> response) {
                    mResultEntityList = response.body();
                    view.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        List<ResultEntity> resultEntity = mResultEntityList.getResults();
                        Collections.sort(resultEntity, new Comparator<ResultEntity>() {
                            @Override
                            public int compare(ResultEntity o1, ResultEntity o2) {
                                return o1.getCollectionName().compareTo(o2.getCollectionName());
                            }
                        });
                        view.setSearchList(mResultEntityList.getResults());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<EntityRepository> call, @NonNull Throwable t) {
                view.toast("Networking error");
            }
        });
    }

    @Override
    public void pushInRV(int position) {
        view.intent(IpTags.ALBUM_ID, String.valueOf(mResultEntityList.getResults().get(position).getCollectionId()));
    }

    @Override
    public void longPushInRV(int position) {}

    @Override
    public Class<?> getNextActivity() {
        return InformationAlbumActivity.class;
    }
}
