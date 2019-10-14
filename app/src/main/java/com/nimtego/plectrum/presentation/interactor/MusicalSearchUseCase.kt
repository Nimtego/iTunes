package com.nimtego.plectrum.presentation.interactor

import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable

interface MusicalSearchUseCase {
    fun searchSong(request: String): Observable<List<Song>>
}