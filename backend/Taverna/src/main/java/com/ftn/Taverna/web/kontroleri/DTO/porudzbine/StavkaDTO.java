package com.ftn.Taverna.web.kontroleri.DTO.porudzbine;

import com.ftn.Taverna.model.Stavka;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StavkaDTO {

    @NotBlank
    private int artikalId;
    @NotEmpty
    private int kolicina;



    public StavkaDTO(Stavka stavka){
        this.artikalId = stavka.getArtikal().getId();
        this.kolicina = stavka.getKolicina();

    }
}
