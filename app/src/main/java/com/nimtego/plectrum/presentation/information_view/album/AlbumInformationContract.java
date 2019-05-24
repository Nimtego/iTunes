package com.nimtego.plectrum.presentation.information_view.album;

import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel;

public interface AlbumInformationContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {
        void viewReady(String albumNameForResponse);

    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void render(AlbumDetailsModel albumDetailsModel);
    }
}
