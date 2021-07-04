package com.ftn.Taverna.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Prodavac {


    @Id
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    @MapsId
    private Korisnik korisnik;

    private Date poslujeOd;
    private String naziv;
    @Column(unique = true,length = 20)
    private String imejl;


    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "prodavac")
    private Set<Artikal> artikli = new HashSet<Artikal>();

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "prodavac")
    private Set<Akcija> akcije = new HashSet<Akcija>();


    @JsonIgnore
    public Set<Artikal> getArtikli() {
        return artikli;
    }


}
