package com.nimtego.itunes.presentation.base;


import io.reactivex.annotations.NonNull;

public abstract class BasePresenter<V extends BaseContract.View,
        I extends BaseContract.Interactor>
        implements BaseContract.Presenter<V, I> {

    protected V view;
    protected I interactor;

    public BasePresenter(I interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attach(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
        this.interactor.dispose();
    }

    @Override
    public V getView() {
        return view;
    }
}
