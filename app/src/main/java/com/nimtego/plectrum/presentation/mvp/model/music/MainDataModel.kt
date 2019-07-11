package com.nimtego.plectrum.presentation.mvp.model.music

data class MainDataModel (
        val artistModels: List<ArtistModel>,
        val albumModels: List<AlbumModel>,
        val songModels: List<SongModel>)