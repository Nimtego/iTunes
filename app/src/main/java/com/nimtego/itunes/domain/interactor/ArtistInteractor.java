package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.presentation.main.model.ArtistModel;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class ArtistInteractor extends BaseInteractor<List<ArtistModel>, ArtistInteractor.Params> {

    public ArtistInteractor() {
    }

    @Override
    protected Observable<List<ArtistModel>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.artists(params.request);
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