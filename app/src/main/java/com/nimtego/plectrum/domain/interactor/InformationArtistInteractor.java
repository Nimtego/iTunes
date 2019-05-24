package com.nimtego.plectrum.domain.interactor;

import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import dagger.internal.Preconditions;
import io.reactivex.Observable;

@Deprecated
public class InformationArtistInteractor
        extends BaseInteractor<ArtistDetailsModelK, InformationArtistInteractor.Params> {
    @Override
    protected Observable<ArtistDetailsModelK> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.artistDetail(params.request).map(result -> {
            List<AlbumModel> albumModels = duplicateRemove(Objects.requireNonNull(result.getAlbums()));
            //todo
            //result.setAlbums(albumModels);
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
