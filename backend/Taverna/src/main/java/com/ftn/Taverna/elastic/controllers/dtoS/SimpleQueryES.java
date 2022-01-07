package com.ftn.Taverna.elastic.controllers.dtoS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleQueryES {
    private String field;
    private String value;
}
