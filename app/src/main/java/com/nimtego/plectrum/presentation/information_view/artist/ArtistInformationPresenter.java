package com.nimtego.plectrum.presentation.information_view.artist;

import com.nimtego.plectrum.domain.interactor.InformationArtistInteractor;
import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.base.BasePresenter;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModel;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModelK;

import java.util.Arrays;

import io.reactivex.observers.DisposableObserver;

class ArtistInformationPresenter
        extends BasePresenter<ArtistInformationContract.View,
        BaseContract.Interactor>
        implements ArtistInformationContract.Presenter<ArtistInformationContract.View,
        BaseContract.Interactor> {


    public ArtistInformationPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public ArtistInformationPresenter() {
        this(new InformationArtistInteractor());
    }

    @Override
    public void viewReady(String albumNameForResponse) {
        interactor.execute(new DisposableObserver<ArtistDetailsModelK>() {
            @Override
            public void onNext(ArtistDetailsModelK artistDetailsModel) {
                ArtistInformationPresenter.this.showArtistInView(artistDetailsModel);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getClass().getCanonicalName() + e.getMessage() + "\n"
                        + Arrays.toString(e.getStackTrace()));
                view.hideLoading();
            }

            @Override
            public void onComplete() {

            }
        }, InformationArtistInteractor.Params.forRequest(albumNameForResponse));
    }

    @Override
    public void albumClicked(AlbumModelK album) {
        view.toast(album.getAlbumName());
    }

    private void showArtistInView(ArtistDetailsModelK artistDetailsModel) {
        view.toast(artistDetailsModel.getArtistName()
                + "\n" + artistDetailsModel.getAlbums().size());
        this.view.render(artistDetailsModel);
    }
}
