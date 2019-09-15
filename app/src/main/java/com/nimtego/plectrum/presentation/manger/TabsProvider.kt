package com.nimtego.plectrum.presentation.manger

import com.nimtego.plectrum.presentation.ui.common.TabContainer
import rx.subjects.PublishSubject

interface TabsProvider {
    fun overrideTabContainer(currentTabContainer: TabContainer?)
    fun getTabChangePublish(): PublishSubject<TabContainer>
}