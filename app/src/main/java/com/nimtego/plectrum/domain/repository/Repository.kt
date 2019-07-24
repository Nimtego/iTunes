package com.nimtego.plectrum.domain.repository

import io.reactivex.Observable

interface Repository<R> {
    fun query(request: String): Observable<R>
}