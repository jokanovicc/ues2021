package com.ftn.Taverna.web.kontroleri.DTO;

import com.ftn.Taverna.model.Artikal;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class ArtikalDTO implements Serializable {

    @NotEmpty
    private Integer id;
    @NotEmpty
    private Integer prodavacId;
    @NotBlank
    private String naziv;
    @NotBlank
    private String opis;
    @Positive
    @NotEmpty
    private Double cena;
    private Double akcijskaCena;

    private byte[] photo;

    public ArtikalDTO(Artikal artikal){
        this.id = artikal.getId();
        this.prodavacId = artikal.getProdavac().getKorisnik().getId();
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
        this.photo = artikal.getPhoto();

    }

}
