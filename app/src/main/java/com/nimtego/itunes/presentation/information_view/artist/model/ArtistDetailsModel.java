package com.nimtego.itunes.presentation.information_view.artist.model;

import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ArtistDetailsModel {
    private String artistName;
    private String artistArtwork;
    private int artistId;
    private List<AlbumModel> albums;
    private String wikiInformation;
}
