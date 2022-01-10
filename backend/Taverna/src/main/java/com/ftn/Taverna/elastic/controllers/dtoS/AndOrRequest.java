package com.ftn.Taverna.elastic.controllers.dtoS;

import lombok.Data;

@Data
public class AndOrRequest {

    private String naziv;
    private Double from;
    private Double to;
    private boolean or;

}
