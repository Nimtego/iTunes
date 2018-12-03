package com.nimtego.itunes.presentation.information_view;

import com.nimtego.itunes.domain.interactor.SongIntTmp;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;

public class DetailedInformationPresenter
        extends BasePresenter<DetailedInformationContract.View,
        BaseContract.Interactor>
        implements DetailedInformationContract.Presenter<DetailedInformationContract.View,
        BaseContract.Interactor> {

    public DetailedInformationPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public DetailedInformationPresenter() {
        this(new SongIntTmp());
    }
}
