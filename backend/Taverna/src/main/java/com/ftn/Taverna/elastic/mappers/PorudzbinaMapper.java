package com.ftn.Taverna.elastic.mappers;

import com.ftn.Taverna.elastic.controllers.dtoS.PorudzbinaESDto;
import com.ftn.Taverna.elastic.model.PorudzbinaES;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public class PorudzbinaMapper {

    public static List<PorudzbinaESDto> mapDtos(SearchHits<PorudzbinaES> searchHits){
        return searchHits
                .map(porudzbina -> mapResponseDTO(porudzbina.getContent()))
                .toList();
    }




    public static PorudzbinaESDto mapResponseDTO(PorudzbinaES porudzbinaES){

        return PorudzbinaESDto.builder()
                .anonimanKomentar(porudzbinaES.isAnonimanKomentar())
                .ocena(porudzbinaES.getOcena())
                .satnica(porudzbinaES.getSatnica())
                .komentar(porudzbinaES.getKomentar())
                .build();


    }



}
