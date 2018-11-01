package com.nimtego.itunes.domain.interactor;

import android.support.v4.app.INotificationSideChannel;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.information_view.InformationAlbumContract;

import io.reactivex.Observable;

public class InformationViewInteractor extends BaseInteractor<Album, InformationViewInteractor.Params>  {

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
