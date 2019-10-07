package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import io.reactivex.Observable

interface RepositoryPopular<R> {
    //todo remove def. response size
    fun query(sectionKey: SectionsKey, responseSize: Int): Observable<R>
}