package com.nimtego.itunes.presentation.base;


import com.nimtego.itunes.data.rest.network.ApiHelper;
import com.nimtego.itunes.domain.Repository;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public interface BaseContract {

    interface Presenter<V extends View, I extends Interactor> {

        void attach(V view);

        void detach();

        V getView();
    }

    interface View<P extends Presenter> {

        void runOnMainThread(Runnable runnable);

        void showLoading();

        void hideLoading();

        void toast(String message);

        void showView(Class<? super View> view, Map<String, String> params);

        void showView(Class<? super View> view);

        P supplyPresenter();
    }
    interface Interactor<T, P> {
        void execute(DisposableObserver<T> observer, P params);
        void dispose();
    }
}

