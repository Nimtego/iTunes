package com.nimtego.plectrum.presentation.main.fragments;

import com.nimtego.plectrum.presentation.base.BaseContract;

public interface MainTabsContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {

    }

    interface View<P extends Presenter> extends BaseContract.View<P> {
        void search(String response);

        boolean isRvEmpty();

        void clearList();

        String getCurrentSerch();

        void setCurrentSearch(String response);
    }
}
