package com.nimtego.itunes.data.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Album {
    private Integer albumId;
    private Integer albumArtistId;
    private String albumName;
    private String albumRealiseDate;
    private Integer albumTrackCount;
    private String albumArtWorkUrl;
    private String albumArtistName;
    private Double albumPrice;
}
