package com.nimtego.plectrum.data.model.rss_itunes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("alternate")
    @Expose
    private String alternate;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

}
