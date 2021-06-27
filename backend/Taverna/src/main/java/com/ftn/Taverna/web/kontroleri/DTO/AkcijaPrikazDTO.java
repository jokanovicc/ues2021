package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Akcija;
import lombok.Data;

import java.sql.Date;

@Data
public class AkcijaPrikazDTO {

    private int id;
    private Date do_kad;
    private Date od_kad;
    private Integer procenat;
    private String tekst;
    private String artikli;




    public AkcijaPrikazDTO(Akcija akcija){
        this.id = akcija.getId();
        this.do_kad = akcija.getDoKad();
        this.od_kad = akcija.getOdKad();
        this.procenat = akcija.getProcenat();
        this.tekst = akcija.getTekst();
    }
}
