package com.nimtego.itunes.presentation.main;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.model.ModelManager;
import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class AlbumsCollectionPresenter
        extends BasePresenter<AlbumsCollectionContract.View,
        BaseContract.Interactor<List<Album>>>
        implements AlbumsCollectionContract.Presenter<AlbumsCollectionContract.View,
        BaseContract.Interactor<List<Album>>> {

    private final String TAG = this.getClass().getCanonicalName();
    private ModelManager mModelManager;

    @Inject
    public AlbumsCollectionPresenter(BaseContract.Interactor<List<Album>> interactor) {
        this.interactor = interactor;
    }

    @Deprecated
    public AlbumsCollectionPresenter() {
        this(new MainViewInteractor(new AppRepository()));
        // TODO: 29.10.2018 replaceable di
        mModelManager = App.getModelManager();
    }

    @Override
    public void search() {
        interactor.execute(new DisposableObserver<List<Album>>() {
            @Override
            public void onNext(List<Album> albums) {
                view.setSearchList(albums);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getMessage());
            }

            @Override
            public void onComplete() {
                AlbumsCollectionPresenter.this.hideViewLoading();
            }
        });
/*        final String requestStr = view.getSearchText();
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
        }*/
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

    private void hideViewLoading() {
        view.hideLoading();
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
