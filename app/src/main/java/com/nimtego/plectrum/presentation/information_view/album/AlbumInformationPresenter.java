package com.nimtego.plectrum.presentation.information_view.album;

import com.nimtego.plectrum.domain.interactor.InformationAlbumInteractor;
import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.base.BasePresenter;
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK;

import io.reactivex.observers.DisposableObserver;

class AlbumInformationPresenter
        extends BasePresenter<AlbumInformationContract.View,
        BaseContract.Interactor>
        implements AlbumInformationContract.Presenter<AlbumInformationContract.View,
        BaseContract.Interactor> {


    public AlbumInformationPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public AlbumInformationPresenter() {
        this(new InformationAlbumInteractor());
    }

    @Override
    public void viewReady(String albumNameForResponse) {
        interactor.execute(new DisposableObserver<AlbumDetailsModelK>() {
            @Override
            public void onNext(AlbumDetailsModelK albumDetailsModel) {
                AlbumInformationPresenter.this.showAlbumsInView(albumDetailsModel);
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

    private void showAlbumsInView(AlbumDetailsModelK albumDetailsModel) {
        view.toast(albumDetailsModel.getAlbumName());
        this.view.render(albumDetailsModel);
    }
}
