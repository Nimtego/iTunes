package com.nimtego.plectrum.presentation.manger

import android.content.Context
import javax.inject.Inject

class AppResourceManager @Inject constructor(
        val context: Context
): ResourceManager {
    override fun getString(resourcesId: Int): String {
        return context.resources.getString(resourcesId)
    }

    override fun getInt(resourcesId: Int): Int {
        return context.resources.getInteger(resourcesId)
    }
}