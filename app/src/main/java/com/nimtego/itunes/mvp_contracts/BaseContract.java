package com.nimtego.itunes.mvp_contracts;


public interface BaseContract {

    interface Presenter<V extends View> {

        void attach(V view);

        void detach();

        V getView();

        Class<?> getNextActivity();

    }

    interface View<P extends Presenter> {

        void runOnMainThread(Runnable runnable);

        void showLoading();

        void hideLoading();

        P supplyPresenter();
    }
}

