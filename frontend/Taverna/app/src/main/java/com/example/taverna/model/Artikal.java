package com.example.taverna.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artikal {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("prodavacId")
    @Expose
    private Integer prodavacId;
    @SerializedName("naziv")
    @Expose
    private String naziv;
    @SerializedName("opis")
    @Expose
    private String opis;
    @SerializedName("cena")
    @Expose
    private Integer cena;

    @SerializedName("akcijskaCena")
    @Expose
    private Integer akcijskaCena;

    @SerializedName("photo")
    @Expose
    private Bitmap photo;


    public Integer getAkcijskaCena() {
        return akcijskaCena;
    }

    public void setAkcijskaCena(Integer akcijskaCena) {
        this.akcijskaCena = akcijskaCena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdavacId() {
        return prodavacId;
    }

    public void setProdavacId(Integer prodavacId) {
        this.prodavacId = prodavacId;
    }

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

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
