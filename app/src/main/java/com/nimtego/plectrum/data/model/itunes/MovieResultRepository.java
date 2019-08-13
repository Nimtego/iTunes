package com.nimtego.plectrum.data.model.itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResultRepository {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<MovieResult> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public List<MovieResult> getResults() {
        return results;
    }
}
