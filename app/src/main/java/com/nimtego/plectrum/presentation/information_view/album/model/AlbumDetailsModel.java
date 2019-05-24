package com.nimtego.plectrum.presentation.information_view.album.model;

import com.nimtego.plectrum.presentation.main.model.SongModel;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class AlbumDetailsModel {
    private String albumName;
    private String albumArtistName;
    private String albumArtwork;
    private int albumId;
    private String albumArtWorkUrl;
    private List<SongModel> songs;
    private Double collectionPrice;
    private String releaseDate;
    private String wikiInformation;
}
