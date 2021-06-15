package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Porudzbina;
import lombok.Data;

@Data
public class KomentarDTO {

    private Integer id;
    private String kupac;
    private String komentar;
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
