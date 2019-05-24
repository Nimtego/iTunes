package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.App
import com.nimtego.plectrum.domain.Repository
import com.nimtego.plectrum.presentation.base.BaseContract
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractorK<T, P> : BaseContract.Interactor<T, P> {
    protected val disposables: CompositeDisposable
    protected var repository: Repository

    init {
        disposables = CompositeDisposable()
        this.repository = App.getRepository()
    }

    protected abstract fun buildUseCaseObservable(params: P): Observable<T>

    override fun execute(observer: DisposableObserver<T>, param: P) {
        val observable = this.buildUseCaseObservable(param)
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
