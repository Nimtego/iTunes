package com.nimtego.plectrum.presentation.ui.auxiliary

import com.nimtego.plectrum.presentation.navigation.Tab

data class SearchTabContainer(
        val listTab: List<Tab>,
        val currentTabNumber: Tab? = listTab[0],
        val listener: (Tab) -> Unit
) : TabContainer {

    override fun getCurrentTab(): Tab? {
        return currentTabNumber
    }

    override fun get(number: Int): Tab? {
        return listTab.firstOrNull { it.getTabNumber() == number }
    }

    override fun listTabs() = listTab
    override fun listener() = listener
}