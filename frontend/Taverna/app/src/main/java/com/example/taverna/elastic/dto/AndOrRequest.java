package com.example.taverna.elastic.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AndOrRequest {

    @SerializedName("naziv")
    @Expose
    private String naziv;

    @SerializedName("from")
    @Expose
    private Integer from;

    @SerializedName("to")
    @Expose
    private Integer to;

    @SerializedName("or")
    @Expose
    private boolean or;


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public boolean isOr() {
        return or;
    }

    public void setOr(boolean or) {
        this.or = or;
    }
}
