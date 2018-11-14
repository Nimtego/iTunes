package com.nimtego.itunes.presentation.information_view.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AlbumDetailsModel {
    private String albumName;
    private String albumArtistName;
    private String albumArtwork;
    private int albumId;
    private String albumArtWorkUrl;
}
