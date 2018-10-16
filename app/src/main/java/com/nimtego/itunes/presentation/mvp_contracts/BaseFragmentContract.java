package com.nimtego.itunes.presentation.mvp_contracts;


public interface BaseFragmentContract {

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

        void toast(String message);

        P supplyPresenter();
    }
}

