package com.nimtego.plectrum.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class Song {
    private Integer artistId;
    private Integer collectionId;
    private Integer trackId;
    private String artistName;
    private String wrapperType;
    private String trackName;
    private Double trackPrice;
    private Integer trackTimeMillis;
}
