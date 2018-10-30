package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.presentation.information_view.InformationAlbumContract;

import io.reactivex.Observable;

public class InformationViewInteractor extends BaseInteractor implements InformationAlbumContract.Interactor {

    @Override
    public Observable<Album> album(String id) {
        return null;
    }

    @Override
    public Observable<Song> song(String id) {
        return null;
    }

    @Override
    public Observable<Artist> artist(String id) {
        return null;
    }
}
