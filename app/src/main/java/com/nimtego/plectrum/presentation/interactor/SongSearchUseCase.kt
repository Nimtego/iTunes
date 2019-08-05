package com.nimtego.plectrum.presentation.interactor

import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable

interface SongSearchUseCase {
    fun searchSong(request: String): Observable<List<Song>>
}