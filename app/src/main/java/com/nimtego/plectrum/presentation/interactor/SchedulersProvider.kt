package com.nimtego.plectrum.presentation.interactor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SchedulersProvider @Inject constructor() {

    open fun ui(): Scheduler = AndroidSchedulers.mainThread()
    open fun computation(): Scheduler = Schedulers.computation()
    open fun io(): Scheduler = Schedulers.io()
    open fun newThread(): Scheduler = Schedulers.newThread()
    open fun trampoline(): Scheduler = Schedulers.trampoline()

}