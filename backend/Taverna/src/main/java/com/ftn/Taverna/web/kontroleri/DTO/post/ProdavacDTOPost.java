package com.ftn.Taverna.web.kontroleri.DTO.post;

import com.ftn.Taverna.model.Prodavac;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;



@Data
@NoArgsConstructor
public class ProdavacDTOPost {


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


    private Integer id;
    @NotNull
    private Date poslujeOd;
    @NotBlank
    private String imejl;
    @NotBlank
    private String adresa;
    @NotBlank
    private String naziv;
    private Integer korisnik;


    public ProdavacDTOPost(Prodavac prodavac){
        this.id = prodavac.getId();
        this.setKorisnik(prodavac.getKorisnik().getId());
        this.setAdresa(prodavac.getKorisnik().getAdresa());
        this.setPoslujeOd(prodavac.getPoslujeOd());
        this.setImejl(prodavac.getImejl());
        this.setNaziv(prodavac.getNaziv());
        this.ime = prodavac.getKorisnik().getIme();
        this.prezime = prodavac.getKorisnik().getPrezime();
        this.korisnicko = prodavac.getKorisnik().getKorisnicko();
        this.sifra = prodavac.getKorisnik().getSifra();
        this.blokiran = prodavac.getKorisnik().isBlokiran();

    }
}
