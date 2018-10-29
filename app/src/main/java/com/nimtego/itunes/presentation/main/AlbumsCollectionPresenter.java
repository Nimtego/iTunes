package com.nimtego.itunes.presentation.main;

import android.util.Log;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.domain.model.ModelManager;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.AlbumsCollectionContract;
import com.nimtego.itunes.presentation.utils.Constant;

import java.util.List;

public class AlbumsCollectionPresenter
        extends BasePresenter<AlbumsCollectionContract.View>
        implements AlbumsCollectionContract.Presenter<AlbumsCollectionContract.View> {

    private final String TAG = this.getClass().getCanonicalName();

    private ModelManager mModelManager;

    public AlbumsCollectionPresenter() {
        mModelManager = App.getModelManager();
    }

    @Override
    public void search() {
        final String requestStr = view.getSearchText();
        if (requestStr.isEmpty()) {
            Log.d(TAG, "BLOCK - <<if (requestStr.isEmpty())>>");
            view.toast(Constant.ERROR_EMPTY_SEARCH_TEXT);
        }
        else {
            mModelManager.getAlbums(new AlbumsCollectionContract.OnFinishedListener() {
                @Override
                public void onFinished(List<Album> albums) {
                    view.setSearchList(albums);
                }

                @Override
                public void onFailure(Throwable t) {
                    view.toast(t.getMessage());
                    Log.d(TAG, t.getMessage());
                }
            }, requestStr);
        }
        /*final String message = view.getSearchText();
        view.toast(message);
        ITunesApi iTunesApi = App.getApi();
        view.showLoading();
        Call<AlbumsRepository> call = iTunesApi.searchAlbum(FabricParam.searchAlbumParam(message, 100));
        call.enqueue(new Callback<AlbumsRepository>() {
            @Override
            public void onResponse(@NonNull Call<AlbumsRepository> call, @NonNull final Response<AlbumsRepository> response) {
                AlbumsRepository mResultEntityList = response.body();
                List<AlbumResult> resultEntity = mResultEntityList.getResults();
                mModelManager.setAlbumCollection(resultEntity, message);
                view.hideLoading();
                Collections.sort(resultEntity, new Comparator<AlbumResult>() {
                    @Override
                    public int compare(AlbumResult o1, AlbumResult o2) {
                        return o1.getCollectionName().compareTo(o2.getCollectionName());
                    }
                });
                view.setSearchList(mModelManager.getListAlbum());
*//*                view.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setSearchList(mResultEntityList.getResults());
                    }
                });*//*
            }

            @Override
            public void onFailure(@NonNull Call<AlbumsRepository> call, @NonNull Throwable t) {
                view.hideLoading();
                view.toast("Networking error");
            }
        });*/
    }

    @Override
    public void pushInRV(int position) {
/*        Map<String, String> params = new HashMap<>();
        String key = IpTags.ALBUM_ID.toString();
        String value = String.valueOf(mModelManager.getListAlbum().get(position).getCollectionId());
        params.put(key, value);
        view.showView(InformationAlbumContract.View.class, params);*/
    }

    @Override
    public void longPushInRV(int position) {
    }

    @Override
    public void viewIsReady() {
/*        if (!mModelManager.getListAlbum().isEmpty())
            view.setSearchList(mModelManager.getListAlbum());
        else
            search();*/
    }

}
