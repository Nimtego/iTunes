package com.nimtego.plectrum.data.model.itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumsResultRepository {

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