package com.nimtego.plectrum.data.network.wiki;

public enum RestCountries {
    RUS("ru"), ENGLISH("en");

    private final String values;

    RestCountries(String values) {
        this.values = values;
    }

    public boolean equalsName(String values) {
        return this.values.equals(values);
    }

    public String toString() {
        return this.values;
    }
}
