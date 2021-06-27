package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AkcijaDodaj {


    @SerializedName("doKad")
    @Expose
    private String doKad;
    @SerializedName("odKad")
    @Expose
    private String odKad;
    @SerializedName("popust")
    @Expose
    private Integer procenat;
    @SerializedName("artikli")
    @Expose
    private List<Integer> artikli;

    @SerializedName("opis")
    @Expose
    private String opis;


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


    public List<Integer> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Integer> artikli) {
        this.artikli = artikli;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
