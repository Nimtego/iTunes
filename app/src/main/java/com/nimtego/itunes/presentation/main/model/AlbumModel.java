package com.nimtego.itunes.presentation.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AlbumModel {
    private String collectionName;
    private String artistName;
    private String artworkUrl100;

    public String getCollectionName() {
        return collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }
}
