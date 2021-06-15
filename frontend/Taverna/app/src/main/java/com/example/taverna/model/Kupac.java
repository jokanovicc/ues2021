package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kupac {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("adresa")
    @Expose
    private String adresa;
    @SerializedName("ime")
    @Expose
    private String ime;
    @SerializedName("prezime")
    @Expose
    private String prezime;
    @SerializedName("korisnicko")
    @Expose
    private String korisnicko;
    @SerializedName("sifra")
    @Expose
    private String sifra;
    @SerializedName("blokiran")
    @Expose
    private Boolean blokiran;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
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

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Boolean getBlokiran() {
        return blokiran;
    }

    public void setBlokiran(Boolean blokiran) {
        this.blokiran = blokiran;
    }


}
