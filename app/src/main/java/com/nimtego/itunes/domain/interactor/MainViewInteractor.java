package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.main.AlbumsCollectionContract;

import java.util.List;

import io.reactivex.Observable;

public class MainViewInteractor
        extends BaseInteractor<List<Album>> {

    public MainViewInteractor(Repository repository) {
        super(repository);
    }

    @Override
    protected Observable<List<Album>> buildUseCaseObservable() {
        return null;
    }

}


/*    public MainViewInteractor(Repository repository) {
        super(repository);
    }

    @Override
    public Observable<List<Album>> albums(String response) {
        return repository.albums();
    }

    @Override
    public Observable<List<Artist>> artists(String response) {
        return repository.artists();
    }

    @Override
    public Observable<List<Song>> songs(String response) {
        return repository.songs();
    }*/

