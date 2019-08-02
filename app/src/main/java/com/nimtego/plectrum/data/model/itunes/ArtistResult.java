package com.nimtego.plectrum.data.model.itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistResult {
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("artistType")
    @Expose
    private String artistType;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("artistLinkUrl")
    @Expose
    private String artistLinkUrl;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("primaryGenreId")
    @Expose
    private Integer primaryGenreId;

    public String getWrapperType() {
        return wrapperType;
    }

    public String getArtistType() {
        return artistType;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistLinkUrl() {
        return artistLinkUrl;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public Integer getPrimaryGenreId() {
        return primaryGenreId;
    }
}