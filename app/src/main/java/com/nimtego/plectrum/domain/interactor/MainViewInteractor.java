package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModelK;
import com.nimtego.plectrum.presentation.main.model.ArtistModel;
import com.nimtego.plectrum.presentation.main.model.ArtistModelK;
import com.nimtego.plectrum.presentation.main.model.MainDataModel;
import com.nimtego.plectrum.presentation.main.model.MainDataModelK;
import com.nimtego.plectrum.presentation.main.model.SongModel;
import com.nimtego.plectrum.presentation.main.model.SongModelK;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class MainViewInteractor
        extends BaseInteractor<MainDataModelK, MainViewInteractor.Params> {

    public MainViewInteractor() {
    }

    @Override
    protected Observable<MainDataModelK> buildUseCaseObservable(MainViewInteractor.Params params) {
        Preconditions.checkNotNull(params);
        Observable<List<AlbumModelK>> albumsObs = repository.albums(params.request);
        Observable<List<SongModelK>> songsObs = repository.songs(params.request);
        Observable<List<ArtistModelK>> artistsObs = repository.artists(params.request);
        return Observable.combineLatest(albumsObs, songsObs, artistsObs, (albums, songs, artists) ->
                new MainDataModelK(artists, albums, songs));
    }

    public static final class Params {

        private final String request;

        private Params(String request) {
            this.request = request;
        }

        public static Params forRequest(String request) {
            return new Params(request);
        }
    }

}
