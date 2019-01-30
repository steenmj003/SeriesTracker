package com.hva.joris.seriestracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Serie{

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("original_name")
    @Expose
    private String name;

    @SerializedName("original_score")
    @Expose
    private Double score;

    public Serie(long id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
