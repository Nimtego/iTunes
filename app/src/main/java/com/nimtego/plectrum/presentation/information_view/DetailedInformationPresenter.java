package com.nimtego.plectrum.presentation.information_view;

import com.nimtego.plectrum.domain.interactor.SongIntTmp;
import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.base.BasePresenter;

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
