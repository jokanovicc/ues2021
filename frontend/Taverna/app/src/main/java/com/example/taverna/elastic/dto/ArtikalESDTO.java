package com.example.taverna.elastic.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ArtikalESDTO {

    @SerializedName("naziv")
    @Expose
    public String naziv;

    @SerializedName("opis")
    @Expose
    public String opis;

    @SerializedName("cena")
    @Expose
    public Double cena;


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
