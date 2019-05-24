package com.nimtego.plectrum.presentation.main.songs;

import com.nimtego.plectrum.domain.interactor.SongInteractor;
import com.nimtego.plectrum.domain.interactor.SongInteractorK;
import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.base.BasePresenter;
import com.nimtego.plectrum.presentation.information_view.DetailedInformationContract;
import com.nimtego.plectrum.presentation.main.model.SongModel;
import com.nimtego.plectrum.presentation.main.model.SongModelK;
import com.nimtego.plectrum.presentation.utils.FragmentType;
import com.nimtego.plectrum.presentation.utils.IpTags;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@Deprecated
public class SongPresenter
        extends BasePresenter<SongContract.View, BaseContract.Interactor>
        implements SongContract.Presenter<SongContract.View, BaseContract.Interactor> {


    public SongPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    @Inject
    public SongPresenter() {
        this(new SongInteractorK());
    }

    @Override
    public void songClicked(SongModelK songModel) {
        Map<String, String> param = new HashMap<>();
        param.put(FragmentType.TYPE.name(), FragmentType.SONG.name());
        param.put(IpTags.SONG_ID.name(), songModel.getSongId());
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
            view.clearList();
            showViewLoading();
            interactor.execute(new DisposableObserver<List<SongModelK>>() {
                @Override
                public void onNext(List<SongModelK> songModel) {
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

    private void showSongsInView(Collection<SongModelK> songModels) {
        view.render(songModels);
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void toast(String message) {
        view.toast(message);
    }
}
