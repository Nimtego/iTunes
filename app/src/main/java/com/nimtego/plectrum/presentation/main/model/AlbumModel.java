package com.nimtego.plectrum.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class AlbumModel {
    private String albumName;
    private String albumArtistName;
    private String albumArtwork;
    private String albumId;
    private String albumArtWorkUrl;

}
