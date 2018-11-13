package com.nimtego.itunes.presentation.main;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class MainPresenter
        extends BasePresenter<MainContract.View,
        BaseContract.Interactor<MainDataModel,
                MainViewInteractor.Params>>
        implements MainContract.Presenter<MainContract.View,
        BaseContract.Interactor<MainDataModel,
                MainViewInteractor.Params>> {

    private final String TAG = this.getClass().getCanonicalName();

    @Inject
    public MainPresenter(BaseContract.Interactor<MainDataModel,
            MainViewInteractor.Params> interactor) {
        super(interactor);
    }


    public MainPresenter() {
        this(null);
        // TODO: 29.10.2018 replaceable di
    }

    @Override
    public void search() {
        showViewLoading();
        view.render(getSearchText());
/*        interactor.execute(new DisposableObserver<MainDataModel>() {
            @Override
            public void onNext(MainDataModel dataModel) {
                MainPresenter.this.showAlbumsInView(dataModel);
            }

            @Override
            public void onError(Throwable e) {
                MainPresenter.this.hideViewLoading();
                MainPresenter.this.toast("error" + e.getLocalizedMessage());
                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

            }

            @Override
            public void onComplete() {
                MainPresenter.this.hideViewLoading();
            }
        }, MainViewInteractor.Params.forRequest(getSearchText()));*/
    }

    @Override
    public void tabSelected(String tabName) {
        //interactor.dispose();
        if (!view.getSearchText().isEmpty())
            search();
    }

/*    private void showAlbumsInView(MainDataModel dataModel) {
        view.render(dataModel);
    }*/

    private String getSearchText() {
        return view.getSearchText();
    }

    private void showViewLoading() {
        view.showLoading();
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void toast(String message) {
        view.toast(message);
    }

    @Override
    public void pushInRV(int position) {
/*        Map<String, String> params = new HashMap<>();
        String key = IpTags.ALBUM_ID.toString();
        String value = String.valueOf(mModelManager.getListAlbum().get(position).getCollectionId());
        params.put(key, value);
        view.showView(InformationAlbumContract.View.class, params);*/
    }

    @Override
    public void longPushInRV(int position) {
    }

    @Override
    public void viewIsReady() {
        if (!view.getSearchText().isEmpty())
            search();
    }

}
