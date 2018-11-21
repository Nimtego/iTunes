package com.nimtego.itunes.presentation.information_view;

import com.nimtego.itunes.presentation.base.BaseContract;

public interface DetailedInformationContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor> extends BaseContract.Presenter<V, I> {

    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

    }
}
