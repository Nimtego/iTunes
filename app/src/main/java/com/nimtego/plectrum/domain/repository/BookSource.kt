package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.presentation.mvp.model.book.Book
import io.reactivex.Observable

interface BookSource {

    fun getBooksByRequest(request: String): Observable<List<Book>>
    fun getBookById(id: Int): Observable<Book>
}