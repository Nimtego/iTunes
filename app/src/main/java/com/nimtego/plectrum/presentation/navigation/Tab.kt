package com.nimtego.plectrum.presentation.navigation

data class Tab(val pair: Pair<Int, String>) {
    fun getTabNumber() = pair.first
    fun getTabName() = pair.second

}