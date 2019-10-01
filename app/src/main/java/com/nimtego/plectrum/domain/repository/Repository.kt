package com.nimtego.plectrum.domain.repository

import io.reactivex.Observable

interface Repository<R> {
    //todo remove def. response size
    fun query(request: String, responseSize: Int = 100): Observable<R>
}