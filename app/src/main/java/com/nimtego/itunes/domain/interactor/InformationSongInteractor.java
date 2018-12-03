package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.information_view.song.model.SongDetailsModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class InformationSongInteractor
        extends BaseInteractor<SongDetailsModel, InformationSongInteractor.Params> {
    @Override
    protected Observable<SongDetailsModel> buildUseCaseObservable(Params params) {
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
