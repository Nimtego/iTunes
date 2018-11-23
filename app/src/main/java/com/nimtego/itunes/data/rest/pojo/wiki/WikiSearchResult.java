package com.nimtego.itunes.data.rest.pojo.wiki;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WikiSearchResult {
    @SerializedName("batchcomplete")
    @Expose
    private String batchcomplete;
    @SerializedName("query")
    @Expose
    private Result query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Result getQuery() {
        return query;
    }

    public void setQuery(Result query) {
        this.query = query;
    }
}
