package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey

enum class PopularMusicKey(val key: String): SectionsKey {
    HOT_TRACK("HotTrack"),
    NEW_TRACK("NewTrack"),
    TOP_TRACK("TopTrack"),
    TOP_ALBUM("TopAlbum")
}