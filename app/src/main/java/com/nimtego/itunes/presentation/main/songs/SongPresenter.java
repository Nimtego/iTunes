package com.nimtego.itunes.presentation.main.songs;

import com.nimtego.itunes.domain.interactor.SongInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.information_view.DetailedInformationContract;
import com.nimtego.itunes.presentation.main.model.SongModel;
import com.nimtego.itunes.presentation.utils.FragmentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

import static com.nimtego.itunes.presentation.utils.IpTags.ALBUM_ID;
import static com.nimtego.itunes.presentation.utils.IpTags.SONG_ID;

public class SongPresenter
        extends BasePresenter<SongContract.View, BaseContract.Interactor>
        implements SongContract.Presenter<SongContract.View, BaseContract.Interactor> {


    public SongPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public SongPresenter() {
        this(new SongInteractor());
    }

    @Override
    public void songClicked(SongModel songModel) {
        Map<String, String> param = new HashMap<>();
        param.put(FragmentType.TYPE.name(), FragmentType.SONG.name());
        param.put(SONG_ID.name(), songModel.getSongId());
        try {
            view.showView(DetailedInformationContract.View.class, param);
        } catch (IllegalArgumentException e) {
            view.toast(e.getMessage());
        }

    }

    @Override
    public void search(String response) {
        if (!view.getCurrentSerch().equals(response) || view.isRvEmpty()) {
            view.setCurrentSearch(response);
            showViewLoading();
            interactor.execute(new DisposableObserver<List<SongModel>>() {
                @Override
                public void onNext(List<SongModel> songModel) {
                    SongPresenter.this.showSongsInView(songModel);
                }

                @Override
                public void onError(Throwable e) {
                    SongPresenter.this.hideViewLoading();
                    SongPresenter.this.toast("error" + e.getLocalizedMessage());
                    // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }

                @Override
                public void onComplete() {
                    SongPresenter.this.hideViewLoading();
                }
            }, SongInteractor.Params.forRequest(response));
        }
    }

    private void showViewLoading() {
        view.showLoading();
    }

    private void showSongsInView(Collection<SongModel> songModels) {
        view.render(songModels);
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void toast(String message) {
        view.toast(message);
    }
}
