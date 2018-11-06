package com.nimtego.itunes.presentation.main;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;
import com.nimtego.itunes.presentation.mapper.AlbumModelDataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class MainPresenter
        extends BasePresenter<MainContract.View,
        BaseContract.Interactor<List<Album>,
                MainViewInteractor.Params>>
        implements MainContract.Presenter<MainContract.View,
        BaseContract.Interactor<List<Album>,
                MainViewInteractor.Params>> {

    private final String TAG = this.getClass().getCanonicalName();
    private AlbumModelDataMapper mapper;

    @Inject
    public MainPresenter(BaseContract.Interactor<List<Album>,
            MainViewInteractor.Params> interactor,
                         AlbumModelDataMapper mapper) {
        this.mapper = mapper;
        this.interactor = interactor;
    }


    public MainPresenter() {
        this(new MainViewInteractor(new AppRepository()), new AlbumModelDataMapper());
        // TODO: 29.10.2018 replaceable di
    }

    @Override
    public void search() {
        showViewLoading();
        toast("Search");
        interactor.execute(new DisposableObserver<List<Album>>() {
            @Override
            public void onNext(List<Album> albums) {
                MainPresenter.this.showAlbumsInView(albums);
            }

            @Override
            public void onError(Throwable e) {
                MainPresenter.this.hideViewLoading();
                MainPresenter.this.toast("error" +e.getLocalizedMessage());
                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

            }

            @Override
            public void onComplete() {
                MainPresenter.this.hideViewLoading();
            }
        }, MainViewInteractor.Params.forRequest(getSearchText()));
    }

    @Override
    public void tabSelected(String tabName) {
        // TODO: 01.11.2018  
    }

    private void showAlbumsInView(Collection<Album> albums) {
        final MainDataModel dataModel = MainDataModel.builder()
                .albumModels(mapper.transformAlbums(albums))
                .build();
        view.render(dataModel);
    }

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
