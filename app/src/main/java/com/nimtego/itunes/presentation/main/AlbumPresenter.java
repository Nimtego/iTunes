package com.nimtego.itunes.presentation.main;

import com.nimtego.itunes.App;
import com.nimtego.itunes.domain.interactor.AlbumInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import javax.inject.Inject;

public class AlbumPresenter
        extends BasePresenter<AlbumContract.View, BaseContract.Interactor>
        implements AlbumContract.Presenter<AlbumContract.View, BaseContract.Interactor> {

    private final String TAG = this.getClass().getCanonicalName();

    @Inject
    public AlbumPresenter(BaseContract.Interactor interactor) {
        this.interactor = interactor;
    }


    public AlbumPresenter() {
        this(new AlbumInteractor());
        // TODO: 29.10.2018 replaceable di
    }

    @Override
    public void albumClicked(AlbumModel albumModel) {

    }
}
