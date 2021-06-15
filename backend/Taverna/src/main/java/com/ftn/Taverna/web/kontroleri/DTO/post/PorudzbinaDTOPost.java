package com.ftn.Taverna.web.kontroleri.DTO.post;


import com.ftn.Taverna.model.Porudzbina;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
public class PorudzbinaDTOPost implements Serializable {


    private Integer id;

    @NotEmpty
    private Integer kupac;

    @NotNull
    private Date satnica;

    @NotEmpty
    private boolean dostavljeno;

    @NotEmpty
    private Integer ocena;

    @NotBlank
    private String komentar;

    @NotEmpty
    private boolean anonimanKomentar;

    @NotEmpty
    private boolean arhiviranKomentar;



    public PorudzbinaDTOPost(Porudzbina porudzbina){
        this.id = porudzbina.getId();
        this.kupac = porudzbina.getKupac().getId();
        this.satnica = porudzbina.getSatnica();
        this.dostavljeno = porudzbina.isDostavljeno();
    }
}
