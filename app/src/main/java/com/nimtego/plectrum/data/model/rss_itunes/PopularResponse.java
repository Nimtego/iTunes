package com.nimtego.plectrum.data.model.rss_itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularResponse {

    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
