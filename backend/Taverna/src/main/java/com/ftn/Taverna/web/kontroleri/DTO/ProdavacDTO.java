package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Prodavac;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
public class ProdavacDTO implements Serializable {

    private Integer id;
    @NotNull
    private Date poslujeOd;
    @NotBlank
    private String imejl;
    @NotBlank
    private String adresa;
    @NotBlank
    private String naziv;
    @NotBlank
    private String ime;
    @NotBlank
    private String sifra;
    private String prezime;
    private String korisnicko;
    private boolean blokiran;
    private Double ocena;


    public ProdavacDTO(Prodavac prodavac){
        this.id = prodavac.getId();
        this.setIme(prodavac.getKorisnik().getIme());
        this.setPrezime(prodavac.getKorisnik().getPrezime());
        this.setKorisnicko(prodavac.getKorisnik().getKorisnicko());
        this.setBlokiran(prodavac.getKorisnik().isBlokiran());
        this.setAdresa(prodavac.getKorisnik().getAdresa());
        this.setPoslujeOd(prodavac.getPoslujeOd());
        this.setImejl(prodavac.getImejl());
        this.setNaziv(prodavac.getNaziv());
        this.setSifra(prodavac.getKorisnik().getSifra());


    }

}
