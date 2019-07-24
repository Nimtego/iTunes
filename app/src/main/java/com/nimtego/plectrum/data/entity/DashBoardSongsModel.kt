package com.nimtego.plectrum.data.entity

import com.nimtego.plectrum.presentation.mvp.model.song.Album
import com.nimtego.plectrum.presentation.mvp.model.song.Song

data class DashBoardSongsModel(val topSongs:  List<Song>,
                               val topAlbums: List<Album>,
                               val newMusic:  List<Song>,
                               val hotTrack:  List<Song>)