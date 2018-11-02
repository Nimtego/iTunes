package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.domain.Repository;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class MainViewInteractor
        extends BaseInteractor<List<Album>, MainViewInteractor.Params> {

    public MainViewInteractor(Repository repository) {
        super(repository);
    }

    @Override
    protected Observable<List<Album>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.albums(params.request);
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
