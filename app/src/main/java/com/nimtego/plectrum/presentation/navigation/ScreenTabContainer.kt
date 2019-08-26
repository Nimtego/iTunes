package com.nimtego.plectrum.presentation.navigation

import ru.terrakok.cicerone.android.support.SupportAppScreen

interface ScreenTabContainer<S : SupportAppScreen> {
    fun getScreen(numberTab: Int): S?
    fun getScreen(nameTab: String): S?
    fun getScreen(tab: Tab): S?
    fun getScreens(): List<S>
    fun getTab(numberTab: Int): Tab
    fun getTab(nameTab: String): Tab
    fun getTab(screen: S): Tab
    fun getTabs(): List<Tab>
}