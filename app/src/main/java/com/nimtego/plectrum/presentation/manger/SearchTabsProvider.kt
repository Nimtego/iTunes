package com.nimtego.plectrum.presentation.manger

import com.nimtego.plectrum.presentation.ui.auxiliary.TabContainer
import rx.subjects.PublishSubject

class SearchTabsProvider : TabsProvider {

    private var currentTabContainer: TabContainer? = null
    private val currentTabContainerPublish: PublishSubject<TabContainer> = PublishSubject.create()

    override fun overrideTabContainer(currentTabContainer: TabContainer?) {
//        if (this.currentTabContainer != currentTabContainer) {
            this.currentTabContainer = currentTabContainer
            this.currentTabContainerPublish.onNext(this.currentTabContainer)
//        }
    }

    override fun getTabChangePublish(): PublishSubject<TabContainer> {
        return this.currentTabContainerPublish
    }
}