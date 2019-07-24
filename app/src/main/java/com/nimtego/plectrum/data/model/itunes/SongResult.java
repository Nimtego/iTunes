package com.nimtego.plectrum.data.model.itunes;

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

    public String getCollectionType() {
        return collectionType;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public Integer getAmgArtistId() {
        return amgArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getKind() {
        return kind;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public Boolean getStreamable() {
        return isStreamable;
    }
}


