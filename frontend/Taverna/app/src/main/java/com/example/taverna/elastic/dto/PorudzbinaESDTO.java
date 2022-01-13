package com.example.taverna.elastic.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Data;


@Data
public class PorudzbinaESDTO {

    @SerializedName("komentar")
    @Expose
    private String komentar;
    @SerializedName("anoniman_komentar")
    @Expose
    private String anoniman_komentar;
    @SerializedName("ocena")
    @Expose
    private Integer ocena;
    @SerializedName("satnica")
    @Expose
    private String satnica;

    @SerializedName("cena")
    @Expose
    private Double cena;

    @SerializedName("kupac")
    @Expose
    private String kupac;


    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getAnoniman_komentar() {
        return anoniman_komentar;
    }

    public void setAnoniman_komentar(String anoniman_komentar) {
        this.anoniman_komentar = anoniman_komentar;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public String getSatnica() {
        return satnica;
    }

    public void setSatnica(String satnica) {
        this.satnica = satnica;
    }


    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }
}
