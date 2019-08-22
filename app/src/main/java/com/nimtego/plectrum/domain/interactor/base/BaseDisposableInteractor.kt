package com.nimtego.plectrum.domain.interactor.base

import com.nimtego.plectrum.presentation.interactor.DisposableInteractor
import dagger.internal.Preconditions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDisposableInteractor(
        protected val disposables: CompositeDisposable
) : DisposableInteractor {

    override fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    protected fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}