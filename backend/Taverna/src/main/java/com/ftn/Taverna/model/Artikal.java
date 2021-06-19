package com.ftn.Taverna.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Artikal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Prodavac prodavac;
    private String naziv;
    private String opis;
    private Double cena;
    @ManyToMany(mappedBy = "artikli",fetch = FetchType.LAZY)
    private Set<Akcija> akcije = new HashSet<Akcija>();
    @Lob
    private byte[] photo;
    private boolean obrisan;


}
