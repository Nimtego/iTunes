package com.nimtego.plectrum.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ArtistModel {
    private String artistName;
    private String artistArtwork;
    private String primaryGenreName;
    private String artistViewUrl;
    private String artistId;
}
