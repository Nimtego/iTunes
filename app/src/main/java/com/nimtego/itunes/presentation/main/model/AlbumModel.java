package com.nimtego.itunes.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AlbumModel {
    private String albumName;
    private String albumArtistName;
    private String albumArtwork;
    private int albumId;
    private String albumArtWorkUrl;

}
