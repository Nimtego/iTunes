package com.nimtego.plectrum.presentation.ui.auxiliary

import com.nimtego.plectrum.presentation.navigation.Tab

interface TabContainer {
    fun listTabs(): List<Tab>
    fun listener(): (Tab) -> Unit
    operator fun get(number: Int): Tab?
    fun getCurrentTab(): Tab?
}