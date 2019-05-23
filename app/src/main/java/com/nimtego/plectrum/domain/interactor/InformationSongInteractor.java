package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

@Deprecated
public class InformationSongInteractor
        extends BaseInteractor<SongDetailsModelK, InformationSongInteractor.Params> {
    @Override
    protected Observable<SongDetailsModelK> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.songDeteil(params.request);
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
