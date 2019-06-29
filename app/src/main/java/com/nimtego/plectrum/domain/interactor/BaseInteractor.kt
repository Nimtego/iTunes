package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.App
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.base.Interactor
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor<T, P> : Interactor<T, P> {
    protected val disposables: CompositeDisposable
    protected var repository: Repository

    init {
        disposables = CompositeDisposable()
        this.repository = App.INSTANCE.getRepository()
    }

    protected abstract fun buildUseCaseObservable(params: P): Observable<T>

    override fun execute(observer: DisposableObserver<T>, params: P) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    override fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}
