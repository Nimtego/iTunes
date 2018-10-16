package com.nimtego.itunes.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Artist {
    private Integer artistId;
    private String artistName;
    private String artistViewUrl;
    private String previewUrl;
}
