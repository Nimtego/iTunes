package com.nimtego.itunes.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntityRepository {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<ResultEntity> results = null;

    private ResultEntity current = null;

    public List<ResultEntity> getResults() {
        return results;
    }


}