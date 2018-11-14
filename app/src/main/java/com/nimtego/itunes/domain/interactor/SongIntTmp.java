package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.model.SongModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class SongIntTmp extends BaseInteractor<SongModel, SongIntTmp.Params> {


    @Override
    protected Observable<SongModel> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.song(params.request);
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
