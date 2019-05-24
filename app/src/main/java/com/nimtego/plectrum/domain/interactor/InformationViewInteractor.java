package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.data.entity.AlbumK;

import io.reactivex.Observable;

@Deprecated
public class InformationViewInteractor extends BaseInteractor<AlbumK, InformationViewInteractor.Params> {

    public InformationViewInteractor() {
    }

    @Override
    protected Observable<AlbumK> buildUseCaseObservable(Params param) {
        return null;
    }

    @Deprecated
    public static final class Params {

        private final String request;

        private Params(String request) {
            this.request = request;
        }

        public static InformationViewInteractor.Params forUser(String request) {
            return new InformationViewInteractor.Params(request);
        }
    }
}
