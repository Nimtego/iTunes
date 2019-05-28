package com.nimtego.plectrum.data.entity

data class DashBoardModel(val topSongs: List<Song>,
                          val topAlbums: List<Album>,
                          val newMusic: List<Song>,
                          val hotTrack: List<Song>) {
}