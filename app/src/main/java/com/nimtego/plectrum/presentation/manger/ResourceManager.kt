package com.nimtego.plectrum.presentation.manger

interface ResourceManager {
    fun getString(resourcesId: Int): String
    fun getInt(resourcesId: Int): Int
}