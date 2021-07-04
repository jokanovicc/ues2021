package com.ftn.Taverna.web.kontroleri.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class IzmenaSifreDTO {

    @NotBlank
    private String novaSifra;
    @NotBlank
    private String staraSifra;
}
