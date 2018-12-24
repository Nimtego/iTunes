package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.information_view.artist.model.ArtistDetailsModel;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

public class InformationArtistInteractor
        extends BaseInteractor<ArtistDetailsModel, InformationArtistInteractor.Params> {
    @Override
    protected Observable<ArtistDetailsModel> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.artistDetail(params.request).map(result -> {
            List<AlbumModel> albumModels = duplicateRemove(result.getAlbums());
            result.setAlbums(albumModels);
            return result;
        });
    }

    private List<AlbumModel> duplicateRemove(List<AlbumModel> albumModels) {
        Map<String, AlbumModel> duplicateCheck = new HashMap<>();
        albumModels
                .forEach(album -> duplicateCheck.put(album.getAlbumName(), album));
        return new ArrayList<>(duplicateCheck.values());
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
