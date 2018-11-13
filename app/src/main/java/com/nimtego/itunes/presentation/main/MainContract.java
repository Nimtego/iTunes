package com.nimtego.itunes.presentation.main;


import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

public interface MainContract {
    interface Presenter<V extends View,
            I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I> {

        void search();

        void tabSelected(String tabName);

        void pushInRV(int position);

        void longPushInRV(int position);

        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

        String getSearchText();

        void render(String response);

        boolean emptyRv();
    }
}
