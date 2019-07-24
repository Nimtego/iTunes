package com.nimtego.plectrum.data.entity.mapper

import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.presentation.mvp.model.book.Book

class PopularBookMapper {
    fun popularResultToBook(result: Result): Book {
        return Book(bookName = result.name,
                bookAuthorName = result.artistName,
                bookId = result.id.toInt(),
                bookArtWorkUrl = result.artworkUrl100,
                bookRealiseDate = result.releaseDate)
    }

}