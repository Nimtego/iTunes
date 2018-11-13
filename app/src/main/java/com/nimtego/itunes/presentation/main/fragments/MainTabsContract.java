package com.nimtego.itunes.presentation.main.fragments;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.AlbumContract;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.Collection;

public interface MainTabsContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I>{

    }
    interface View<P extends Presenter> extends BaseContract.View<P>{
        void search(String response);
    }
}
