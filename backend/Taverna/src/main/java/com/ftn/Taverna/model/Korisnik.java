package com.ftn.Taverna.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;
    @Column(unique = true,length = 20)
    private String korisnicko;
    private String sifra;
    private String adresa;
    private boolean blokiran;
    @Enumerated(EnumType.STRING)
    private Roles roles;


}
