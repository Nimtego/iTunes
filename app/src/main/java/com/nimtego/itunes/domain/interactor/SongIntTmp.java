package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.main.model.SongModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class SongIntTmp extends BaseInteractor<SongModel, SongIntTmp.Params> {


    @Override
    protected Observable<SongModel> buildUseCaseObservable(Params params) {
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
