package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Porudzbina;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class KomentarDTO {

    @NotEmpty
    private Integer id;
    @NotBlank
    private String kupac;
    @NotBlank
    private String komentar;
    @NotEmpty
    @Positive
    private Integer ocena;
    private boolean arhiviran;
    private boolean anoniman;


    public KomentarDTO(Porudzbina porudzbina){
        this.id = porudzbina.getId();
        this.kupac = porudzbina.getKupac().getKorisnik().getKorisnicko();
        this.komentar = porudzbina.getKomentar();
        this.ocena = porudzbina.getOcena();
        this.arhiviran = porudzbina.isArhiviranKomentar();
        this.anoniman = porudzbina.isAnonimanKomentar();


    }
}
