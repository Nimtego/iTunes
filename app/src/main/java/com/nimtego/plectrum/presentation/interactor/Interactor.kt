package com.nimtego.plectrum.presentation.interactor

import io.reactivex.observers.DisposableObserver

interface Interactor<T, P> {
    fun execute(observer: DisposableObserver<T>, params: P)
    fun dispose()
}