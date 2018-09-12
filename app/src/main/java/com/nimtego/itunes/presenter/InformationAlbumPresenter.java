package com.nimtego.itunes.presenter;

import com.nimtego.itunes.mvp_contracts.InformationAlbumContract;

public class InformationAlbumPresenter extends BasePresenter<InformationAlbumContract.View>
        implements InformationAlbumContract.Presenter<InformationAlbumContract.View> {
    @Override
    public Class<?> getNextActivity() {
        return null;
    }
}
