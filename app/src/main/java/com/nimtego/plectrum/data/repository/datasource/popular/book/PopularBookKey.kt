package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey

enum class PopularBookKey(val key: String): SectionsKey {
    TOP_FREE_BOOK("TopFreeBook"),
    TOP_PAID_BOOK("TopPaidBook")
}