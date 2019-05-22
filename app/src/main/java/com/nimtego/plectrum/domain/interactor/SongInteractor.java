package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.main.model.SongModelK;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class SongInteractor extends BaseInteractor<List<SongModelK>, SongInteractor.Params> {

    public SongInteractor() {
    }

    @Override
    protected Observable<List<SongModelK>> buildUseCaseObservable(Params params) {
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
