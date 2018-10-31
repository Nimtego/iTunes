package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.information_view.InformationAlbumContract;

import io.reactivex.Observable;

public class InformationViewInteractor extends BaseInteractor<Album>  {

    public InformationViewInteractor(Repository repository) {
        super(repository);
    }

    @Override
    protected Observable<Album> buildUseCaseObservable() {
        return null;
    }
}
