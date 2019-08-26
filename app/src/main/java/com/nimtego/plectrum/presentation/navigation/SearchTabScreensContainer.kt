package com.nimtego.plectrum.presentation.navigation

import ru.terrakok.cicerone.android.support.SupportAppScreen

data class SearchTabScreensContainer(
        var screens: Map<Tab, SupportAppScreen>
) : ScreenTabContainer<SupportAppScreen> {
    override fun getScreens(): List<SupportAppScreen> {
        return screens.values.toList()
    }

    override fun getTabs(): List<Tab> {
        return screens.keys.toList()
    }

    override fun getScreen(tab: Tab): SupportAppScreen? {
        return screens[tab]
    }

    override fun getTab(numberTab: Int): Tab {
        return screens.keys.first {
            it.getTabNumber() == numberTab
        }
    }

    override fun getTab(nameTab: String): Tab {
        return screens.keys.first {
            it.getTabName() == nameTab
        }
    }

    override fun getTab(screen: SupportAppScreen): Tab {
        return screens.filterValues {
            it == screen
        }.keys.first()
    }

    override fun getScreen(numberTab: Int): SupportAppScreen? {
        return screens[getTab(numberTab)]
    }

    override fun getScreen(nameTab: String): SupportAppScreen? {
        return screens[getTab(nameTab)]
    }
}