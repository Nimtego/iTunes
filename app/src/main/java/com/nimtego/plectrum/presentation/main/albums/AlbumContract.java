package com.nimtego.plectrum.presentation.main.albums;

import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.main.fragments.MainTabsContract;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;

import java.util.Collection;

public interface AlbumContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor> extends MainTabsContract.Presenter<V, I> {
        void albumClicked(AlbumModel albumModel);

        void search(String response);
    }

    interface View<P extends Presenter> extends MainTabsContract.View<P> {
        void render(Collection<AlbumModel> albumModel);
    }
}
