package com.nimtego.itunes.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SongModel {
    private String trackName;
    private String trackAlbumName;
    private String trackArtistName;
    private String trackArtwork;

}
