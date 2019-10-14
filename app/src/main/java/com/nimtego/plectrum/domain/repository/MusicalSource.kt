package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.presentation.mvp.model.song.Song

interface MusicalSource : AuthorSource<ArtistResult>,
                          SongSource<SongResult>,
                          AlbumSource<AlbumResult>