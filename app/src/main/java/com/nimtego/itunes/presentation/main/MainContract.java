package com.nimtego.itunes.presentation.main;


import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.MainDataModel;


import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public interface MainContract {
    interface Presenter<V extends View,
                        I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {

        void search();

        void tabSelected(String tabName);

        void pushInRV(int position);

        void longPushInRV(int position);

        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

        String getSearchText();

        void render(MainDataModel dataModel);
    }
}
