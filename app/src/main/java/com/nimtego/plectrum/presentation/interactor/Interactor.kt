package com.nimtego.plectrum.presentation.interactor

import io.reactivex.observers.DisposableObserver

interface Interactor<T, P> : DisposableInteractor{
    fun execute(observer: DisposableObserver<T>, params: P)
}