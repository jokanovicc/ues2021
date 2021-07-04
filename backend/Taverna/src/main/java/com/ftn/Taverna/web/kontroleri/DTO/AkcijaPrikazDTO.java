package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Akcija;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class AkcijaPrikazDTO {

    @NotNull
    private int id;
    @FutureOrPresent
    @NotEmpty
    private Date do_kad;
    @NotEmpty
    @FutureOrPresent
    private Date od_kad;
    @Positive
    @NotEmpty
    private Integer procenat;
    @NotBlank
    private String tekst;
    @NotBlank
    private String artikli;




    public AkcijaPrikazDTO(Akcija akcija){
        this.id = akcija.getId();
        this.do_kad = akcija.getDoKad();
        this.od_kad = akcija.getOdKad();
        this.procenat = akcija.getProcenat();
        this.tekst = akcija.getTekst();
    }
}
