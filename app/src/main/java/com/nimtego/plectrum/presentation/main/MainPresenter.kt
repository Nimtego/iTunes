package com.nimtego.plectrum.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.base.BasePresenter
import com.nimtego.plectrum.presentation.mvp.view.MainView

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

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