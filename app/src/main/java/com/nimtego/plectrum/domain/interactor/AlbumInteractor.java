package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.main.model.AlbumModel;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

@Deprecated
public class AlbumInteractor
        extends BaseInteractor<List<AlbumModel>, AlbumInteractor.Params> {

    public AlbumInteractor() {
    }

    @Override
    protected Observable<List<AlbumModel>> buildUseCaseObservable(Params params) {
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
