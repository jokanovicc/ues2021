package com.ftn.Taverna.web.kontroleri.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AkcijaDTO {

    private String opis;
    private Date odKad;
    private Date doKad;
    private Integer popust;
    private List<Integer> artikli;

}
