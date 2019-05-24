package com.nimtego.plectrum.presentation.information_view.song.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class SongDetailsModel {
    private String songName;
    private String songArtistName;
    private String songArtwork;
    private String songAlbumName;
    private int albumId;
    private String albumArtWorkUrl;
    private Double songPrice;
    private String releaseDate;
    private String wikiInformation;
}
