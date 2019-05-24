package com.nimtego.plectrum.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.mvp.MainView

@InjectViewState
class MainPresenterK : BasePresenterK<MainView>() {

    private val TAG = this.javaClass.canonicalName

    private var currentSearch = ""
    private var currentTab = ""

    override fun attachView(view: MainView) {
        super.attachView(view)
        viewIsReady()
    }

    fun search(search: String) {
        if (currentSearch != search) {
            viewState.render(search)
        }
    }

    fun tabSelected(tabName: String) {
        viewState.render(currentSearch)
    }

    fun viewIsReady() {
        if (!currentSearch.isEmpty())
            search(currentSearch)
    }

}