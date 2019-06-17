package com.nimtego.plectrum.domain.repository

import io.reactivex.Observable

interface RepositoryK<R> {
    fun query(request: String): Observable<R>
}