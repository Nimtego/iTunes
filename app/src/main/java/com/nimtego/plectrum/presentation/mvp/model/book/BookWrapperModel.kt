package com.nimtego.plectrum.presentation.mvp.model.book

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

class BookWrapperModel (
        private val book: Book
) : ChildViewModel {

    override fun mainName(): String {
        return this.book.bookName
    }

    override fun minorName(): String {
        return this.book.bookAuthorName
    }

    override fun imageUrl(): String {
        return this.book.bookArtWorkUrl
    }

    override fun id(): String {
        return this.book.bookId.toString()
    }
}