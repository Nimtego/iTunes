package com.nimtego.plectrum.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.MainViewInteractorK
import com.nimtego.plectrum.presentation.base.BaseContract
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.main.model.MainDataModelK
import com.nimtego.plectrum.presentation.mvp.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenterK @Inject
constructor(interactor: BaseContract.Interactor<MainDataModelK, MainViewInteractorK.Params>?)
    : BasePresenterK<MainView>() {

    private val TAG = this.javaClass.canonicalName


    constructor() : this(null) {
        // TODO: 29.10.2018 replaceable di
    }

    fun search() {
        viewState.render("") //todo remove
    }

    fun tabSelected(tabName: String) {
        search()
    }

    fun viewIsReady() {
        //todo need change
//        if (!view.searchText.isEmpty())
//            search()
    }

}