package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.domain.Repository;

import io.reactivex.Observable;

public class InformationViewInteractor extends BaseInteractor<Album, InformationViewInteractor.Params> {

    public InformationViewInteractor(Repository repository) {
        super(repository);
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
