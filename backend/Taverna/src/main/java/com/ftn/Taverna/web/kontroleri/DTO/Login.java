package com.ftn.Taverna.web.kontroleri.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Login {

    @NotEmpty
    private String korisnicko;
    @NotEmpty
    private String sifra;
}
