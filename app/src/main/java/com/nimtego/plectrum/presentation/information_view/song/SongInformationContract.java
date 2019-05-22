package com.nimtego.plectrum.presentation.information_view.song;

import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK;

public interface SongInformationContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {
        void viewReady(String albumNameForResponse);

    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void render(SongDetailsModelK songDetailsModel);
    }
}
