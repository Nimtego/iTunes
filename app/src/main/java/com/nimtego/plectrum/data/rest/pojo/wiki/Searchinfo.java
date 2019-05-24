package com.nimtego.plectrum.data.rest.pojo.wiki;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Searchinfo {
    @SerializedName("totalhits")
    @Expose
    private Integer totalhits;

    public Integer getTotalhits() {
        return totalhits;
    }

    public void setTotalhits(Integer totalhits) {
        this.totalhits = totalhits;
    }

}