package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PorudzbinePrikaz {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kupac")
    @Expose
    private Integer kupac;
    @SerializedName("satnica")
    @Expose
    private String satnica;
    @SerializedName("dostavljeno")
    @Expose
    private Boolean dostavljeno;
    @SerializedName("artikli")
    @Expose
    private String artikli;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKupac() {
        return kupac;
    }

    public void setKupac(Integer kupac) {
        this.kupac = kupac;
    }

    public String getSatnica() {
        return satnica;
    }

    public void setSatnica(String satnica) {
        this.satnica = satnica;
    }

    public Boolean getDostavljeno() {
        return dostavljeno;
    }

    public void setDostavljeno(Boolean dostavljeno) {
        this.dostavljeno = dostavljeno;
    }

    public String getArtikli() {
        return artikli;
    }

    public void setArtikli(String artikli) {
        this.artikli = artikli;
    }
}
