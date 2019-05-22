package com.nimtego.plectrum.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@Builder
@ToString
@Deprecated
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
