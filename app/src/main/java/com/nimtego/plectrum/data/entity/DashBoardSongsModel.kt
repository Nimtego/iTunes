package com.nimtego.plectrum.data.entity

data class DashBoardSongsModel(val topSongs: List<Song>,
                               val topAlbums: List<Album>,
                               val newMusic: List<Song>,
                               val hotTrack: List<Song>)