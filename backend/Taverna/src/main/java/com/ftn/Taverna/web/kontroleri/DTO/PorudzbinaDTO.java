package com.ftn.Taverna.web.kontroleri.DTO;


import com.ftn.Taverna.model.Porudzbina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PorudzbinaDTO implements Serializable {

    private Integer id;
    @NotNull
    private Integer kupac;

    @NotNull
    private Date satnica;

    @NotEmpty
    private boolean dostavljeno;

    private String artikli;

    
    public PorudzbinaDTO(Porudzbina porudzbina){
        this.id = porudzbina.getId();
        this.kupac = porudzbina.getKupac().getId();
        this.satnica = porudzbina.getSatnica();
        this.dostavljeno = porudzbina.isDostavljeno();

    }



}
