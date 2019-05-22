package com.nimtego.plectrum.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Deprecated
public class Artist {
    private String primaryGenreName;
    private String artistName;
    private String artistViewUrl;
    private String previewUrl;
}
