package com.nimtego.plectrum.presentation.manger

import javax.inject.Inject

class MusicalItemStorageImp @Inject constructor() : MusicalItemStorage {
    override fun currentSong(): Int? {
        return 1
    }
}