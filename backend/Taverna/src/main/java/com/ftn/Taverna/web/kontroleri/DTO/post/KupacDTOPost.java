package com.ftn.Taverna.web.kontroleri.DTO.post;

import com.ftn.Taverna.model.Kupac;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class KupacDTOPost {

    private Integer id;
    @NotBlank
    private String adresa;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    @NotBlank
    private String korisnicko;
    @NotBlank
    private String sifra;
    @NotBlank
    private boolean blokiran;


    public KupacDTOPost(Kupac kupac){
        this.setId(kupac.getId());
        this.setAdresa(kupac.getKorisnik().getAdresa());
        this.ime = kupac.getKorisnik().getIme();
        this.prezime = kupac.getKorisnik().getPrezime();
        this.korisnicko = kupac.getKorisnik().getKorisnicko();
        this.sifra = kupac.getKorisnik().getKorisnicko();
        this.blokiran = kupac.getKorisnik().isBlokiran();

    }
}
