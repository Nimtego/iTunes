package com.nimtego.plectrum.data.model.itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResult {
    
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("collectionArtistId")
    @Expose
    private Integer collectionArtistId;
    @SerializedName("collectionArtistViewUrl")
    @Expose
    private String collectionArtistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;
    @SerializedName("trackRentalPrice")
    @Expose
    private Double trackRentalPrice;
    @SerializedName("collectionHdPrice")
    @Expose
    private Double collectionHdPrice;
    @SerializedName("trackHdPrice")
    @Expose
    private Double trackHdPrice;
    @SerializedName("trackHdRentalPrice")
    @Expose
    private Double trackHdRentalPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("discNumber")
    @Expose
    private Integer discNumber;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("trackNumber")
    @Expose
    private Integer trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String contentAdvisoryRating;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("hasITunesExtras")
    @Expose
    private Boolean hasITunesExtras;

    public String getWrapperType() {
        return wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public Integer getCollectionArtistId() {
        return collectionArtistId;
    }

    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
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

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public Double getTrackRentalPrice() {
        return trackRentalPrice;
    }

    public Double getCollectionHdPrice() {
        return collectionHdPrice;
    }

    public Double getTrackHdPrice() {
        return trackHdPrice;
    }

    public Double getTrackHdRentalPrice() {
        return trackHdRentalPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
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

    public Integer getTrackCount() {
        return trackCount;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public Boolean getHasITunesExtras() {
        return hasITunesExtras;
    }

}
