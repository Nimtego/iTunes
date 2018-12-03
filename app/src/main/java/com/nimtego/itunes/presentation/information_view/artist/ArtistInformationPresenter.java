package com.nimtego.itunes.presentation.information_view.artist;

import com.nimtego.itunes.domain.interactor.InformationAlbumInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;

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
        this(new InformationAlbumInteractor());
    }

    @Override
    public void viewReady(String albumNameForResponse) {
        interactor.execute(new DisposableObserver<AlbumDetailsModel>() {
            @Override
            public void onNext(AlbumDetailsModel albumDetailsModel) {
                ArtistInformationPresenter.this.showAlbumsInView(albumDetailsModel);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getClass().getCanonicalName() + e.getMessage());
                view.hideLoading();
            }

            @Override
            public void onComplete() {

            }
        }, InformationAlbumInteractor.Params.forRequest(albumNameForResponse));
    }

    private void showAlbumsInView(AlbumDetailsModel albumDetailsModel) {
        view.toast(albumDetailsModel.getAlbumName());
        this.view.render(albumDetailsModel);
    }
}
