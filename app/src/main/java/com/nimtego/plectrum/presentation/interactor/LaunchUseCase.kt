package com.nimtego.plectrum.presentation.interactor

import io.reactivex.Completable

interface LaunchUseCase {
    fun coldStart(): Completable
}