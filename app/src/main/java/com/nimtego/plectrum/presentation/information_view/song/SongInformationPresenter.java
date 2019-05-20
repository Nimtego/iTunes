package com.nimtego.plectrum.presentation.information_view.song;

import com.nimtego.plectrum.domain.interactor.InformationSongInteractor;
import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.base.BasePresenter;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel;

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
        this(new InformationSongInteractor());
    }

    @Override
    public void viewReady(String songNameForResponse) {
        interactor.execute(new DisposableObserver<SongDetailsModel>() {
            @Override
            public void onNext(SongDetailsModel songDetailsModel) {
                SongInformationPresenter.this.showSongInView(songDetailsModel);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getClass().getCanonicalName() + e.getMessage());
                view.hideLoading();
            }

            @Override
            public void onComplete() {

            }
        }, InformationSongInteractor.Params.forRequest(songNameForResponse));
    }

    private void showSongInView(SongDetailsModel songDetailsModel) {
        view.toast(songDetailsModel.getSongName());
        this.view.render(songDetailsModel);
    }
}
