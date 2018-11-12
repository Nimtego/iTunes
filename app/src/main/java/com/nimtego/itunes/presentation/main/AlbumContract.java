package com.nimtego.itunes.presentation.main;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.Collection;
import java.util.List;

public interface AlbumContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor> extends BaseContract.Presenter<V, I> {
        void albumClicked(AlbumModel albumModel);
    }
    interface View<P extends Presenter> extends BaseContract.View<P> {
        void render(Collection<AlbumModel> albumModel);
    }
}
