package com.nimtego.itunes.presentation.main.artists;

import com.nimtego.itunes.domain.interactor.ArtistInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.model.ArtistModel;

import java.util.Collection;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ArtistPresenter 
        extends BasePresenter<ArtistContract.View, BaseContract.Interactor>
        implements ArtistContract.Presenter<ArtistContract.View, BaseContract.Interactor> {

    public ArtistPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public ArtistPresenter() {
        this(new ArtistInteractor());
        // TODO: 29.10.2018 replaceable di
    }

    @Override
    public void artistClicked(ArtistModel albumModel) {
        view.toast(albumModel.getArtistName());
    }

    @Override
    public void search(String response) {
        if (!view.getCurrentSerch().equals(response)) {
            view.setCurrentSearch(response);
            showViewLoading();
            interactor.execute(new DisposableObserver<List<ArtistModel>>() {
                @Override
                public void onNext(List<ArtistModel> dataModel) {
                    ArtistPresenter.this.showAlbumsInView(dataModel);
                }

                @Override
                public void onError(Throwable e) {
                    ArtistPresenter.this.hideViewLoading();
                    ArtistPresenter.this.toast("error" + e.getLocalizedMessage());
                    // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }

                @Override
                public void onComplete() {
                    ArtistPresenter.this.hideViewLoading();
                }
            }, ArtistInteractor.Params.forRequest(response));
        }
    }

    private void showViewLoading() {
        view.showLoading();
    }

    private void showAlbumsInView(Collection<ArtistModel> albumModels) {
        view.render(albumModels);
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void toast(String message) {
        view.toast(message);
    }
}
