package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.information_view.artist.model.ArtistDetailsModel;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class InformationArtistInteractor
        extends BaseInteractor<ArtistDetailsModel, InformationArtistInteractor.Params> {
    @Override
    protected Observable<ArtistDetailsModel> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.artistDetail(params.request);
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
