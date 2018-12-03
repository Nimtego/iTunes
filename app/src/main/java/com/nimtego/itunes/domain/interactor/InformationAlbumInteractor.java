package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class InformationAlbumInteractor
        extends BaseInteractor<AlbumDetailsModel, InformationAlbumInteractor.Params> {


    @Override
    protected Observable<AlbumDetailsModel> buildUseCaseObservable(Params params) {
/*        Preconditions.checkNotNull(params);
        Observable<AlbumDetailsModel>> albumObs = repository.album(params.request);
        Observable<List<SongModel>> songsObs = repository.songs(params.request);
        Observable<List<ArtistModel>> artistsObs = repository.artists(params.request);
        return Observable.combineLatest(albumsObs, songsObs, artistsObs, (albums, songs, artists) ->
                MainDataModel.builder()
                        .albumModels(albums)
                        .songModels(songs)
                        .artistModels(artists)
                        .build());*/
        Preconditions.checkNotNull(params);
        return repository.album(params.request);
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
