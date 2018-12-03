package com.nimtego.itunes.presentation.information_view.song;

import com.nimtego.itunes.domain.interactor.InformationAlbumInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;

import io.reactivex.observers.DisposableObserver;

class SongInformationPresenter
        extends BasePresenter<SongInformationContract.View,
        BaseContract.Interactor>
        implements SongInformationContract.Presenter<SongInformationContract.View,
        BaseContract.Interactor> {


    public SongInformationPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public SongInformationPresenter() {
        this(new InformationAlbumInteractor());
    }

    @Override
    public void viewReady(String albumNameForResponse) {
        interactor.execute(new DisposableObserver<AlbumDetailsModel>() {
            @Override
            public void onNext(AlbumDetailsModel albumDetailsModel) {
                SongInformationPresenter.this.showAlbumsInView(albumDetailsModel);
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
