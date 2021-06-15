package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Kupac;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class KupacDTO implements Serializable {

    private Integer id;
    @NotBlank
    private String adresa;
    @NotBlank
    private String ime;
    private String prezime;
    private String korisnicko;
    private boolean blokiran;

    public KupacDTO(Kupac kupac){
        this.setId(kupac.getId());
        this.setAdresa(kupac.getKorisnik().getAdresa());
        this.setIme(kupac.getKorisnik().getIme());
        this.setPrezime(kupac.getKorisnik().getPrezime());
        this.setKorisnicko(kupac.getKorisnik().getKorisnicko());
        this.setBlokiran(kupac.getKorisnik().isBlokiran());

    }



}
