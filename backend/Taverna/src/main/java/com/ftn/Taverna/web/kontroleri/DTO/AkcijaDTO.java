package com.ftn.Taverna.web.kontroleri.DTO;


import com.ftn.Taverna.model.Akcija;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AkcijaDTO {


    private Integer id;
    private ProdavacDTO prodavac;
    private Integer procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;


    public AkcijaDTO(Akcija akcija){
        this.id = akcija.getId();
        this.prodavac = new ProdavacDTO(akcija.getProdavac());
        this.procenat = akcija.getProcenat();
        this.odKad = akcija.getOdKad();
        this.doKad = akcija.getDoKad();
        this.tekst = akcija.getTekst();
    }



}
