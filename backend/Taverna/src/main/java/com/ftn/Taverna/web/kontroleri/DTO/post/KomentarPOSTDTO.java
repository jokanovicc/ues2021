package com.ftn.Taverna.web.kontroleri.DTO.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class KomentarPOSTDTO {


    @NotEmpty
    private Integer kupac;
    @NotBlank
    private String komentar;
    @NotEmpty
    private Integer ocena;
    private boolean anoniman;
    @NotEmpty
    private Integer porudzbina;

}
