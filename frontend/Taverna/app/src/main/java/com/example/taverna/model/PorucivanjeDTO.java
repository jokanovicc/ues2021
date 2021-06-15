package com.example.taverna.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PorucivanjeDTO {


    @SerializedName("listaStavki")
    @Expose
    private List<StavkaDTO> listaStavki = new ArrayList<>();


    public List<StavkaDTO> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaDTO> listaStavki) {
        this.listaStavki = listaStavki;
    }
}
