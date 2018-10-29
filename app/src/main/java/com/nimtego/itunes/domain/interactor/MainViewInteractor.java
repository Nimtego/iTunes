package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.presentation.base.BaseInteractor;
import com.nimtego.itunes.presentation.main.AlbumsCollectionContract;

import java.util.List;

import io.reactivex.Observable;

public class MainViewInteractor extends BaseInteractor implements AlbumsCollectionContract.Interactor {
    @Override
    public Observable<List<Album>> albums(String response) {
        return null;
    }

    @Override
    public Observable<List<Artist>> artists(String response) {
        return null;
    }

    @Override
    public Observable<List<Song>> songs(String response) {
        return null;
    }
}
