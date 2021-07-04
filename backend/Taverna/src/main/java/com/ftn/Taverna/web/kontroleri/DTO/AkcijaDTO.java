package com.ftn.Taverna.web.kontroleri.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AkcijaDTO {

    @NotBlank
    private String opis;

    @FutureOrPresent
    @NotEmpty
    private Date odKad;
    @FutureOrPresent
    @NotEmpty
    private Date doKad;
    @Positive
    @NotEmpty
    private Integer popust;
    @NotEmpty
    private List<Integer> artikli;

}
