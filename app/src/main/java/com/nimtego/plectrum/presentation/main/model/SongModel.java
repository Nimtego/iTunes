package com.nimtego.plectrum.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class SongModel {
    private String trackName;
    private String trackAlbumName;
    private String trackArtistName;
    private String trackArtwork;
    private String songId;

}
