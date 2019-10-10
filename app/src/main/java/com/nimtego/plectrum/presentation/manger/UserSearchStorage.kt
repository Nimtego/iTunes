package com.nimtego.plectrum.presentation.manger

import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import javax.inject.Inject

class UserSearchStorage @Inject constructor() : UserSearchItemStorage {

    private var currentSearchText: String = ""
    private val currentSearchTextBehavior: BehaviorSubject<String> = BehaviorSubject.create()
    private val currentSearchTextPublish: PublishSubject<String> = PublishSubject.create()

    override fun getCurrentSearchText(): String {
        return this.currentSearchText
    }

    override fun overrideCurrentSearchText(text: String) {
        this.currentSearchText = text
        this.currentSearchTextBehavior.onNext(this.currentSearchText)
        this.currentSearchTextPublish.onNext(this.currentSearchText)
    }

    override fun getCurrentSearchTextBehavior(): BehaviorSubject<String> {
        return this.currentSearchTextBehavior
    }

    override fun getCurrentSearchTextPublish(): PublishSubject<String> {
        return this.currentSearchTextPublish
    }
}