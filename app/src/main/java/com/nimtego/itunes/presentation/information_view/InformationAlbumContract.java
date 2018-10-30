package com.nimtego.itunes.presentation.information_view;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.presentation.base.BaseContract;

import java.util.List;

import io.reactivex.Observable;

public interface InformationAlbumContract {
    interface Presenter<V extends View, I extends Interactor> extends BaseContract.Presenter<V, I> {
        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void setImageAlbum(String url);
        void setArtistName(String name);
        void setAlbumName(String nameAlbum);
        void setSongList(List<String> songs);
        void setAlbumInformation(String information);
    }

    interface Interactor extends BaseContract.Interactor{
        Observable<Album> album(String id);
        Observable<Song> song(String id);
        Observable<Artist> artist(String id);
    }
}
