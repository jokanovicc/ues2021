package com.ftn.Taverna.elastic.controllers.dtoS;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PorudzbinaESDto {

    private String komentar;
    private boolean anonimanKomentar;
    private Integer ocena;
    private Date satnica;

}
