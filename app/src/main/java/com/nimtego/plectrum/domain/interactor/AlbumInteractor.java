package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModelK;
import com.nimtego.plectrum.presentation.main.model.MainDataModelK;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class AlbumInteractor
        extends BaseInteractor<List<AlbumModelK>, AlbumInteractor.Params> {

    public AlbumInteractor() {
    }

    @Override
    protected Observable<List<AlbumModelK>> buildUseCaseObservable(Params params) {
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
