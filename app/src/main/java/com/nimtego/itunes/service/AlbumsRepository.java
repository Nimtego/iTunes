package com.nimtego.itunes.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumsRepository {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<AlbumResult> results = null;

    private AlbumResult current = null;

    public List<AlbumResult> getResults() {
        return results;
    }


}