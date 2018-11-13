package com.nimtego.itunes.presentation.main.artists;

import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.fragments.MainTabsContract;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;

import java.util.Collection;

public interface ArtistContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor> extends MainTabsContract.Presenter<V, I> {
        void artistClicked(ArtistModel albumModel);
        void search(String response);
    }
    interface View<P extends Presenter> extends MainTabsContract.View<P> {
        void render(Collection<ArtistModel> artistModels);
    }
}
