package com.nimtego.itunes.presentation.information_view.artist;

import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.domain.interactor.InformationAlbumInteractor;
import com.nimtego.itunes.domain.interactor.InformationArtistInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;
import com.nimtego.itunes.presentation.information_view.artist.model.ArtistDetailsModel;

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
        interactor.execute(new DisposableObserver<ArtistDetailsModel>() {
            @Override
            public void onNext(ArtistDetailsModel artistDetailsModel) {
                ArtistInformationPresenter.this.showArtistInView(artistDetailsModel);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getClass().getCanonicalName() + e.getMessage());
                view.hideLoading();
            }

            @Override
            public void onComplete() {

            }
        }, InformationArtistInteractor.Params.forRequest(albumNameForResponse));
    }

    private void showArtistInView(ArtistDetailsModel artistDetailsModel) {
        view.toast(artistDetailsModel.getArtistName());
        this.view.render(artistDetailsModel);
    }
}
