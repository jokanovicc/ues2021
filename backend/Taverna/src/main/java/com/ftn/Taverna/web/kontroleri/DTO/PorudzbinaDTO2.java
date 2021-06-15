package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Porudzbina;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
public class PorudzbinaDTO2 {

    private Integer id;
    @NotNull
    private Integer kupac;

    @NotNull
    private Date satnica;

    @NotEmpty
    private boolean dostavljeno;

    private List<Integer> stavke;

    public PorudzbinaDTO2(Porudzbina porudzbina){

        this.id = porudzbina.getId();
        this.kupac = porudzbina.getKupac().getId();
        this.satnica = porudzbina.getSatnica();
        this.dostavljeno = porudzbina.isDostavljeno();


    }

}
