package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Korisnik;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class KorisnikDTO {

    @NotEmpty
    private Integer id;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    @NotBlank
    private String korisnicko;
    @NotBlank
    private boolean blokiran;
    @NotBlank
    private String adresa;
    @NotBlank
    private String token;
    @NotBlank
    private String role;

    public KorisnikDTO(Korisnik korisnik){
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnicko = korisnik.getKorisnicko();
        this.blokiran = korisnik.isBlokiran();
        this.role = korisnik.getRoles().toString();
        this.adresa = korisnik.getAdresa();
    }


}
