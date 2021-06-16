package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IzmenaSifreDTO {

    @SerializedName("novaSifra")
    @Expose
    private String novaSifra;

    @SerializedName("staraSifra")
    @Expose
    private String staraSifra;

    public String getNovaSifra() {
        return novaSifra;
    }

    public void setNovaSifra(String novaSifra) {
        this.novaSifra = novaSifra;
    }

    public String getStaraSifra() {
        return staraSifra;
    }

    public void setStaraSifra(String staraSifra) {
        this.staraSifra = staraSifra;
    }
}
