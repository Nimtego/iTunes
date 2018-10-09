package com.nimtego.itunes.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {
    private Integer artistId;
    private String artistName;
    private String artistViewUrl;
    private String previewUrl;
}
