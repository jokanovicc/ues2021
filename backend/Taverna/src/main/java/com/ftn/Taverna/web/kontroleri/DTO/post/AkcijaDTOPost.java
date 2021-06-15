package com.ftn.Taverna.web.kontroleri.DTO.post;


import com.ftn.Taverna.model.Akcija;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AkcijaDTOPost {

    private Integer id;
    private Integer prodavac;
    private Integer procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;





    public AkcijaDTOPost(Akcija akcija){
        this.id = akcija.getId();
        this.prodavac = akcija.getProdavac().getId();
        this.procenat = akcija.getProcenat();
        this.odKad = akcija.getOdKad();
        this.doKad = akcija.getDoKad();
        this.tekst = akcija.getTekst();
    }







}
