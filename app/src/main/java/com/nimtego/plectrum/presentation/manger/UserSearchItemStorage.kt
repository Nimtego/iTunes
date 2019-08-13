package com.nimtego.plectrum.presentation.manger

import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject

interface UserSearchItemStorage {
    fun getCurrentSearchText(): String
    fun overrideCurrentSearchText(text: String)
    fun getCurrentSearchTextBehavior(): BehaviorSubject<String>
    fun getCurrentSearchTextPublish(): PublishSubject<String>
}