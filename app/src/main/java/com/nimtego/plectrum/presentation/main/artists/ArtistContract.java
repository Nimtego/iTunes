package com.nimtego.plectrum.presentation.main.artists;

import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.main.fragments.MainTabsContract;
import com.nimtego.plectrum.presentation.main.model.ArtistModel;

import java.util.Collection;

@Deprecated
public interface ArtistContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor> extends MainTabsContract.Presenter<V, I> {
        void artistClicked(ArtistModel albumModel);

        void search(String response);
    }

    interface View<P extends Presenter> extends MainTabsContract.View<P> {
        void render(Collection<ArtistModel> artistModels);
    }
}
