package com.nimtego.plectrum.presentation.manger

import rx.subjects.BehaviorSubject

interface UserSearchItemStorage {
    fun getCurrentSearchText(): String
    fun overrideCurrentSearchText(text: String)
    fun getCurrentSearchTextObservable(): BehaviorSubject<String>
}