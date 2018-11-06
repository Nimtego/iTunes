package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;

import java.util.List;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class SongInteractor extends BaseInteractor<List<Song>, SongInteractor.Params> {

    public SongInteractor(Repository repository) {
        super(repository);
    }

    @Override
    protected Observable<List<Song>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.songs(params.request);
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
