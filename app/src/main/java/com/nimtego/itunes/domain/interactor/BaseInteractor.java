package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.presentation.base.BaseContract;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseInteractor implements BaseContract.Interactor {
    private final CompositeDisposable disposables;

    public BaseInteractor() {
        disposables = new CompositeDisposable();
    }

    @Override
    public void dispose() {
        if (!disposables.isDisposed())
            disposables.dispose();
    }
}
