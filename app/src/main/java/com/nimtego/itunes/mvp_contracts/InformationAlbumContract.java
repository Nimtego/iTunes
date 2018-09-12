package com.nimtego.itunes.mvp_contracts;

public interface InformationAlbumContract {
    interface Presenter<V extends View> extends BaseContract.Presenter<V> {
        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void setImageAlbum(String url);
        void setArtistName(String name);
        void setAlbumName(String nameAlbum);
    }
}
