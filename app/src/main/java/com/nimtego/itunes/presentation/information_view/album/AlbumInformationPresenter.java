package com.nimtego.itunes.presentation.information_view.album;

import com.nimtego.itunes.domain.interactor.BaseInteractor;
import com.nimtego.itunes.domain.interactor.InformationAlbumInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;

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
        interactor.execute(new DisposableObserver<AlbumDetailsModel>() {
            @Override
            public void onNext(AlbumDetailsModel albumDetailsModel) {
                AlbumInformationPresenter.this.showAlbumsInView(albumDetailsModel);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getMessage());
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
