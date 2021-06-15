package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DodavanjeKomentara {

    @SerializedName("kupac")
    @Expose
    private Integer kupac;
    @SerializedName("komentar")
    @Expose
    private String komentar;
    @SerializedName("ocena")
    @Expose
    private Integer ocena;
    @SerializedName("anoniman")
    @Expose
    private Boolean anoniman;
    @SerializedName("porudzbina")
    @Expose
    private Integer porudzbina;

    public Integer getKupac() {
        return kupac;
    }

    public void setKupac(Integer kupac) {
        this.kupac = kupac;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public Boolean getAnoniman() {
        return anoniman;
    }

    public void setAnoniman(Boolean anoniman) {
        this.anoniman = anoniman;
    }

    public Integer getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Integer porudzbina) {
        this.porudzbina = porudzbina;
    }

}

