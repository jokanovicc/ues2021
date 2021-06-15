package com.ftn.Taverna.web.kontroleri.DTO.porudzbine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PorucivanjeDTO {

    private List<StavkaDTO> listaStavki = new ArrayList<>();


}
