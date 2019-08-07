package com.nimtego.plectrum.data.model.rss_itunes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("artistId")
    @Expose
    private String artistId;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String contentAdvisoryRating;
    @SerializedName("artistUrl")
    @Expose
    private String artistUrl;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("url")
    @Expose
    private String url;

    public String getArtistName() {
        return artistName;
    }

    public String getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getUrl() {
        return url;
    }
}