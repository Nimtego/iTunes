package com.nimtego.itunes.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongResult {
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("collectionType")
    @Expose
    private String collectionType;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("amgArtistId")
    @Expose
    private Integer amgArtistId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("discNumber")
    @Expose
    private Integer discNumber;
    @SerializedName("trackNumber")
    @Expose
    private Integer trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;
    @SerializedName("isStreamable")
    @Expose
    private Boolean isStreamable;

    public String getWrapperType() {
        return wrapperType;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getCountry() {
        return country;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTrackName() {
        return trackName;
    }

}


