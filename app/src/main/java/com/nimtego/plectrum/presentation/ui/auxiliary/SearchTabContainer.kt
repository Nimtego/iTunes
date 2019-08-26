package com.nimtego.plectrum.presentation.ui.auxiliary

import com.nimtego.plectrum.presentation.navigation.Tab

data class SearchTabContainer(
        val listTab: List<Tab>,
        val listener: (String) -> Unit
) : TabContainer {
    override fun listTabs() = listTab
    override fun listener() = listener
}