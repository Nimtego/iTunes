package com.nimtego.plectrum.presentation.ui.auxiliary

import com.nimtego.plectrum.presentation.navigation.Tab

interface TabContainer {
    fun listTabs(): List<Tab>
    fun listener(): (String) -> Unit
}