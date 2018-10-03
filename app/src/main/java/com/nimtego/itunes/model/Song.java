package com.nimtego.itunes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
