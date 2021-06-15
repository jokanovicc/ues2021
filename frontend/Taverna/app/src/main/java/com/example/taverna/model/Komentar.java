package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Komentar {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kupac")
    @Expose
    private String kupac;
    @SerializedName("komentar")
    @Expose
    private String komentar;
    @SerializedName("ocena")
    @Expose
    private Integer ocena;
    @SerializedName("arhiviran")
    @Expose
    private Boolean arhiviran;
    @SerializedName("anoniman")
    @Expose
    private Boolean anoniman;
    @SerializedName("porudzbina")
    @Expose
    private Integer porudzbina;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
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


    public Boolean getArhiviran() {
        return arhiviran;
    }

    public void setArhiviran(Boolean arhiviran) {
        this.arhiviran = arhiviran;
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
