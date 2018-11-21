package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class InformationAlbumInteractor
        extends BaseInteractor<AlbumDetailsModel, InformationAlbumInteractor.Params> {


    @Override
    protected Observable<AlbumDetailsModel> buildUseCaseObservable(Params param) {
        Preconditions.checkNotNull(param);
        return repository.album(param.request);
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
