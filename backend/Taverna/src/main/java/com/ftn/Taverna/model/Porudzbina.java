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
public class Porudzbina {

    @Id
    private Integer id;
    @ManyToOne
    private Kupac kupac;
    private Date satnica;
    private boolean dostavljeno;

    private Integer ocena;
    private String komentar;
    private boolean anonimanKomentar;
    private boolean arhiviranKomentar;


    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "porudzbina")
    private List<Stavka> stavke = new ArrayList<>();




}
