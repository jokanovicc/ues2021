package com.ftn.Taverna.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Stavka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer kolicina;
    @ManyToOne
    private Artikal artikal;
    @ManyToOne
    private Porudzbina porudzbina;

}
