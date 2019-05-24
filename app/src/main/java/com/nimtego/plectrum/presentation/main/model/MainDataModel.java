package com.nimtego.plectrum.presentation.main.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MainDataModel {
    private List<ArtistModel> artistModels;
    private List<AlbumModel> albumModels;
    private List<SongModel> songModels;
}
