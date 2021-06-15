package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prodavac {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("poslujeOd")
    @Expose
    private String poslujeOd;
    @SerializedName("imejl")
    @Expose
    private String imejl;
    @SerializedName("adresa")
    @Expose
    private String adresa;
    @SerializedName("naziv")
    @Expose
    private String naziv;
    @SerializedName("sifra")
    @Expose
    private String sifra;
    @SerializedName("ime")
    @Expose
    private String ime;
    @SerializedName("prezime")
    @Expose
    private String prezime;
    @SerializedName("korisnicko")
    @Expose
    private String korisnicko;
    @SerializedName("blokiran")
    @Expose
    private Boolean blokiran;
    @SerializedName("ocena")
    @Expose
    private Double ocena;


    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoslujeOd() {
        return poslujeOd;
    }

    public void setPoslujeOd(String poslujeOd) {
        this.poslujeOd = poslujeOd;
    }

    public String getImejl() {
        return imejl;
    }

    public void setImejl(String imejl) {
        this.imejl = imejl;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnicko() {
        return korisnicko;
    }

    public void setKorisnicko(String korisnicko) {
        this.korisnicko = korisnicko;
    }

    public Boolean getBlokiran() {
        return blokiran;
    }

    public void setBlokiran(Boolean blokiran) {
        this.blokiran = blokiran;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
