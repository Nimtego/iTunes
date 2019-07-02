package com.nimtego.plectrum.presentation.mvp.view_model.music

data class MainDataModel (
        val artistModels: List<ArtistModel>,
        val albumModels: List<AlbumModel>,
        val songModels: List<SongModel>)