package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AkcijaPrikaz {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("do_kad")
    @Expose
    private String doKad;
    @SerializedName("od_kad")
    @Expose
    private String odKad;
    @SerializedName("procenat")
    @Expose
    private Integer procenat;
    @SerializedName("tekst")
    @Expose
    private String tekst;
    @SerializedName("artikli")
    @Expose
    private String artikli;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoKad() {
        return doKad;
    }

    public void setDoKad(String doKad) {
        this.doKad = doKad;
    }

    public String getOdKad() {
        return odKad;
    }

    public void setOdKad(String odKad) {
        this.odKad = odKad;
    }

    public Integer getProcenat() {
        return procenat;
    }

    public void setProcenat(Integer procenat) {
        this.procenat = procenat;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getArtikli() {
        return artikli;
    }

    public void setArtikli(String artikli) {
        this.artikli = artikli;
    }
}
