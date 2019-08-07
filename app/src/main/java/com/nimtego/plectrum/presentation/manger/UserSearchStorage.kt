package com.nimtego.plectrum.presentation.manger

import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import javax.inject.Inject

class UserSearchStorage @Inject constructor() : UserSearchItemStorage {

    private var currentSearchText: String = ""
    private val currentSearchTextObservable: BehaviorSubject<String> = BehaviorSubject.create()

    override fun getCurrentSearchText(): String {
        return this.currentSearchText
    }

    override fun overrideCurrentSearchText(text: String) {
        if (this.currentSearchText != text) {
            this.currentSearchText = text
            this.currentSearchTextObservable.onNext(this.currentSearchText)
        }
    }

    override fun getCurrentSearchTextObservable(): BehaviorSubject<String> {
        return this.currentSearchTextObservable
    }
}