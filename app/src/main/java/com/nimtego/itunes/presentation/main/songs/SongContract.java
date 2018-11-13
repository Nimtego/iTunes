package com.nimtego.itunes.presentation.main.songs;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.fragments.MainTabsContract;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.Collection;

public interface SongContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor> extends MainTabsContract.Presenter<V, I> {
        void songClicked(SongModel songModel);
        void search(String response);
    }
    interface View<P extends Presenter> extends MainTabsContract.View<P> {
        void render(Collection<SongModel> songModels);
    }
}
