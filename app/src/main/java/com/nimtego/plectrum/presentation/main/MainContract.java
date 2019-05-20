package com.nimtego.plectrum.presentation.main;


import com.nimtego.plectrum.presentation.base.BaseContract;

public interface MainContract {
    interface Presenter<V extends View,
            I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {

        void search();

        void tabSelected(String tabName);

        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

        String getSearchText();

        void render(String response);

        boolean emptyRv();
    }
}