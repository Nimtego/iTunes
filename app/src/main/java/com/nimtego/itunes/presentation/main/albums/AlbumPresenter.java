package com.nimtego.itunes.presentation.main.albums;

import com.nimtego.itunes.domain.interactor.AlbumInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class AlbumPresenter
        extends BasePresenter<AlbumContract.View, BaseContract.Interactor>
        implements AlbumContract.Presenter<AlbumContract.View, BaseContract.Interactor> {

    private final String TAG = this.getClass().getCanonicalName();

    @Inject
    public AlbumPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }


    public AlbumPresenter() {
        this(new AlbumInteractor());
        // TODO: 29.10.2018 replaceable di
    }

    @Override
    public void albumClicked(AlbumModel albumModel) {
        toast(albumModel.getAlbumName());
    }

    @Override
    public void search(String response) {
        if (!view.getCurrentSerch().equals(response)) {
            view.setCurrentSearch(response);
            showViewLoading();
            interactor.execute(new DisposableObserver<List<AlbumModel>>() {
                @Override
                public void onNext(List<AlbumModel> dataModel) {
                    AlbumPresenter.this.showAlbumsInView(dataModel);
                }

                @Override
                public void onError(Throwable e) {
                    AlbumPresenter.this.hideViewLoading();
                    AlbumPresenter.this.toast("error" + e.getLocalizedMessage());
                    // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }

                @Override
                public void onComplete() {
                    AlbumPresenter.this.hideViewLoading();
                }
            }, AlbumInteractor.Params.forRequest(response));
        }
    }

    private void showViewLoading() {
        view.showLoading();
    }

    private void showAlbumsInView(Collection<AlbumModel> albumModels) {
        view.render(albumModels);
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void toast(String message) {
        view.toast(message);
    }
}
