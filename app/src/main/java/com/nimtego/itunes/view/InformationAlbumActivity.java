package com.nimtego.itunes.view;

import android.os.Bundle;

import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.InformationAlbumContract;
import com.nimtego.itunes.presenter.InformationAlbumPresenter;

public class InformationAlbumActivity
        extends BaseView<InformationAlbumContract.Presenter>
        implements InformationAlbumContract.View<InformationAlbumContract.Presenter>  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_album);
    }

    @Override
    public InformationAlbumContract.Presenter supplyPresenter() {
        return new InformationAlbumPresenter();
    }
}
