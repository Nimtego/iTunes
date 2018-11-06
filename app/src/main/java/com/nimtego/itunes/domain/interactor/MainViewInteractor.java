package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.MainDataModel;
import com.nimtego.itunes.presentation.mapper.AlbumModelDataMapper;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.BiFunction;

public class MainViewInteractor
        extends BaseInteractor<MainDataModel, MainViewInteractor.Params> {

    public MainViewInteractor(Repository repository) {
        super(repository);
    }

    @Override
    protected Observable<MainDataModel> buildUseCaseObservable(MainViewInteractor.Params params) {
        Preconditions.checkNotNull(params);
        AlbumModelDataMapper mapper = new AlbumModelDataMapper();
        Observable<List<Album>> albObs = repository.albums(params.request);
        Observable<List<Song>> songObs = repository.songs(params.request);

        return Observable.zip(repository.albums(params.request), repository.songs(params.request),
                new BiFunction<List<Album>, List<Song>, MainDataModel>() {
                    @Override
                    public MainDataModel apply(List<Album> albums, List<Song> songs) throws Exception {
                        return MainDataModel.builder().build();
                    }
                });
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
