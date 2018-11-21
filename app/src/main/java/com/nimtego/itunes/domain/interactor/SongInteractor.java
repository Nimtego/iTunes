package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class SongInteractor extends BaseInteractor<List<SongModel>, SongInteractor.Params> {

    public SongInteractor() {
    }

    @Override
    protected Observable<List<SongModel>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.songs(params.request);
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
