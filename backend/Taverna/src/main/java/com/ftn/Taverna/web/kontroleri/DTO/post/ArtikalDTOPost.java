package com.ftn.Taverna.web.kontroleri.DTO.post;

import com.ftn.Taverna.model.Artikal;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ArtikalDTOPost implements Serializable {

    private Integer id;
    @NotBlank
    private String naziv;
    @NotBlank
    private String opis;
    @NotBlank
    private Double cena;

    private List<Integer> artikli = new ArrayList<>();

    private byte[] photo;


    public ArtikalDTOPost(Artikal artikal){
        this.id = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
        this.photo = artikal.getPhoto();

    }


}
