package com.ftn.Taverna.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Akcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Prodavac prodavac;

    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.LAZY)
    @JoinTable(name="artikli_akcije", joinColumns = {@JoinColumn(name = "akcija_id")},
    inverseJoinColumns = {@JoinColumn(name = "artikal_id")})
    private List<Artikal> artikli = new ArrayList<>();

    private Integer procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;
}
