package com.nimtego.plectrum.data.model.itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistsResultRepository {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<ArtistResult> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public List<ArtistResult> getResults() {
        return results;
    }
}
