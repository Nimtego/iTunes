package com.nimtego.plectrum.presentation.information_view.artist;

import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModel;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModelK;

@Deprecated
public interface ArtistInformationContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {
        void viewReady(String albumNameForResponse);

        void albumClicked(AlbumModelK album);
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void render(ArtistDetailsModelK albumDetailsModel);
    }
}
