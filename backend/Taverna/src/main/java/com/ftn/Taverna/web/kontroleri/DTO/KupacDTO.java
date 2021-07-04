package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Kupac;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class KupacDTO implements Serializable {

    @NotEmpty
    private Integer id;
    @NotBlank
    private String adresa;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    @NotBlank
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
