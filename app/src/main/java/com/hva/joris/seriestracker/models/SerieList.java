package com.hva.joris.seriestracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SerieList {

    @SerializedName("results")
    private List<Serie> series;

    public SerieList(List<Serie> series) {
        this.series = series;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}
