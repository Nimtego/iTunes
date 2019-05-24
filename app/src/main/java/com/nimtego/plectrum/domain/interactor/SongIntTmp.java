package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.main.model.SongModelK;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

//todo this class is really need?
public class SongIntTmp extends BaseInteractor<SongModelK, SongIntTmp.Params> {


    @Override
    protected Observable<SongModelK> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.songs(params.request).map(s -> s.get(0));
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
