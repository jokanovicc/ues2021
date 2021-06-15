package com.ftn.Taverna.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Kupac {
    @Id
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    @MapsId
    private Korisnik korisnik;
}
