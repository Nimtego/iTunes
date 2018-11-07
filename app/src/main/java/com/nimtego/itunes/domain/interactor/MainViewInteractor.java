package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.MainDataModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class MainViewInteractor
        extends BaseInteractor<MainDataModel, MainViewInteractor.Params> {

    public MainViewInteractor(Repository repository) {
        super(repository);
    }

    @Override
    protected Observable<MainDataModel> buildUseCaseObservable(MainViewInteractor.Params params) {
        Preconditions.checkNotNull(params);
        Observable<List<AlbumModel>> albumsObs = repository.albums(params.request);
        Observable<List<SongModel>> songsObs = repository.songs(params.request);
        Observable<List<ArtistModel>> artistsObs = repository.artists(params.request);
        return Observable.combineLatest(albumsObs, songsObs, artistsObs, (albums, songs, artists) ->
                MainDataModel.builder()
                        .albumModels(albums)
                        .songModels(songs)
                        .artistModels(artists)
                        .build());
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
