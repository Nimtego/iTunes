package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.data.entity.Album;

import io.reactivex.Observable;

public class InformationViewInteractor extends BaseInteractor<Album, InformationViewInteractor.Params> {

    public InformationViewInteractor() {
    }

    @Override
    protected Observable<Album> buildUseCaseObservable(Params param) {
        return null;
    }


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
