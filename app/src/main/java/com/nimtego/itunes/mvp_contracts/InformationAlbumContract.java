package com.nimtego.itunes.mvp_contracts;

public interface InformationAlbumContract {
    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

    }
}
