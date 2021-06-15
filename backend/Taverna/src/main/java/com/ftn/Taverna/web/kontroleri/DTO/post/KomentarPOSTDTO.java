package com.ftn.Taverna.web.kontroleri.DTO.post;

import lombok.Data;

@Data
public class KomentarPOSTDTO {


    private Integer kupac;
    private String komentar;
    private Integer ocena;
    private boolean anoniman;
    private Integer porudzbina;

}
