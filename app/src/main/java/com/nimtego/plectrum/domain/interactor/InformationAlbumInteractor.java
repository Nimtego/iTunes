package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class InformationAlbumInteractor
        extends BaseInteractor<AlbumDetailsModel, InformationAlbumInteractor.Params> {


    @Override
    protected Observable<AlbumDetailsModel> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.albumDeteil(params.request);
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
