package com.nimtego.plectrum.presentation.base

import io.reactivex.observers.DisposableObserver

interface Interactor<T, P> {
    fun execute(observer: DisposableObserver<T>, params: P)
    fun dispose()
}