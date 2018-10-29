package com.nimtego.itunes.presentation.information_view;

import com.nimtego.itunes.presentation.base.BaseContract;

import java.util.List;

public interface InformationAlbumContract {
    interface Presenter<V extends View, I extends Interactor> extends BaseContract.Presenter<V, I> {
        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void setImageAlbum(String url);
        void setArtistName(String name);
        void setAlbumName(String nameAlbum);
        void setSongList(List<String> songs);
        void setAlbumInformation(String information);
    }

    interface Interactor extends BaseContract.Interactor{
        // TODO: 29.10.2018
    }
}
