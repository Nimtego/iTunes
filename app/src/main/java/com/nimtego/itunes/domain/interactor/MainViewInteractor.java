package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.MainDataModel;
import com.nimtego.itunes.presentation.main.model.SongModel;
import com.nimtego.itunes.presentation.mapper.AlbumModelDataMapper;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

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
/*        return songsObs.map(new Function<List<SongModel>, MainDataModel>() {
                                 @Override
                                 public MainDataModel apply(List<SongModel> albumModels) throws Exception {
                                     return MainDataModel.builder().songModels(albumModels).build();
                                 }
                             });*/
        return Observable.zip(albumsObs, songsObs, artistsObs, (albums, songs, artists) ->
                MainDataModel.builder()
                        .albumModels(albums)
                        .songModels(songs)
                        .artistModels(artists)
                        .build());
    }


/*    @Override
    protected Observable<MainDataModel> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        Observable.just(repository.albums(params.))

        });
        return repository.dataModel(params.request);
    }*/

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
