package com.nimtego.plectrum.data.model.itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongsRepository {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<SongResult> results = null;

    public List<SongResult> getResults() {
        return results;
    }

}
