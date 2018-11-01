package com.nimtego.itunes.domain.interactor;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.base.BaseContract;

import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseInteractor<T, P> implements BaseContract.Interactor<T, P> {
    protected final CompositeDisposable disposables;
    protected Repository repository;

    @Inject
    public BaseInteractor(Repository repository) {
        disposables = new CompositeDisposable();
        this.repository = repository;
    }

    protected abstract Observable<T> buildUseCaseObservable(P param);

    @Override
    public void execute(DisposableObserver<T> observer, P param) {
        final Observable<T> observable = this.buildUseCaseObservable(param);
        // TODO: 31.10.2018 class
/*                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());*/
        addDisposable(observable.subscribeWith(observer));
    }

    @Override
    public void dispose() {
        if (!disposables.isDisposed())
            disposables.dispose();
    }

    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
