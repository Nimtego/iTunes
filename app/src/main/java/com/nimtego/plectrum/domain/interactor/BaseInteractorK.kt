package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.App
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.domain.repository.RepositoryK
import com.nimtego.plectrum.presentation.base.Interactor
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractorK<R, P>(
        protected var repository: RepositoryK<R>
) : Interactor<R, P> {

    private var disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: P): Observable<R>

    override fun execute(observer: DisposableObserver<R>, params: P) {
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

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }
}
