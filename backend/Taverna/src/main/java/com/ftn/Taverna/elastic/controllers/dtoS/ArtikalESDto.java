package com.ftn.Taverna.elastic.controllers.dtoS;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtikalESDto {

    private String naziv;
    private String opis;
    private Double cena;
    private Integer komentara;
    private Double rating;
}
