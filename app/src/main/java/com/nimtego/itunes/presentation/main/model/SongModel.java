package com.nimtego.itunes.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SongModel {
    private String songName;
    private String songAlbumName;
    private String songArtistName;
    private String songArtwork;
}
