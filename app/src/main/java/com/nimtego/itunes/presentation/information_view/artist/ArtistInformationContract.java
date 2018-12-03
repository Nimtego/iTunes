package com.nimtego.itunes.presentation.information_view.artist;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;

public interface ArtistInformationContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {
        void viewReady(String albumNameForResponse);

    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void render(AlbumDetailsModel albumDetailsModel);
    }
}
