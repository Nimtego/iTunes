package com.nimtego.itunes.data.rest.pojo.wiki;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WikiSearchResult {
    @SerializedName("batchcomplete")
    @Expose
    private String batchcomplete;
    @SerializedName("continue")
    @Expose
    private Continue _continue;
    @SerializedName("query")
    @Expose
    private Result query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Continue getContinue() {
        return _continue;
    }

    public void setContinue(Continue _continue) {
        this._continue = _continue;
    }

    public Result getQuery() {
        return query;
    }

    public void setQuery(Result query) {
        this.query = query;
    }

    public boolean isEmpty() {
        return query.getSearch().isEmpty();
    }

}