package com.nimtego.itunes.presentation.mvp_contracts;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.domain.model.ModelManager;


import java.util.List;

public interface AlbumsCollectionContract {
    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

        void search();

        void pushInRV(int position);

        void longPushInRV(int position);

        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

        String getSearchText();

        void clearList();

        void setSearchList(List<AlbumResult> list);
    }
    interface OnFinishedListener {

        void onFinished(List<Album> albums);
        void onFailure(Throwable t);
    }
}
