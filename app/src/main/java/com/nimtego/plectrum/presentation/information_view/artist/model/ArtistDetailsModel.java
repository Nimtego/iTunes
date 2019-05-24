package com.nimtego.plectrum.presentation.information_view.artist.model;

import com.nimtego.plectrum.presentation.main.model.AlbumModel;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class ArtistDetailsModel {
    private String artistName;
    private String artistArtwork;
    private int artistId;
    private List<AlbumModel> albums;
    private String wikiInformation;
}
