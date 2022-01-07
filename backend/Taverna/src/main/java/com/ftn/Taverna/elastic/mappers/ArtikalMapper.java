package com.ftn.Taverna.elastic.mappers;

import com.ftn.Taverna.elastic.controllers.dtoS.ArtikalESDto;
import com.ftn.Taverna.elastic.model.ArtikalES;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.ArrayList;
import java.util.List;

public class ArtikalMapper {

    public static List<ArtikalESDto> mapDtos(SearchHits<ArtikalES> searchHits) {
        return searchHits
                .map(artikal -> mapResponseDto(artikal.getContent()))
                .toList();
    }

    public static ArtikalESDto mapResponseDto(ArtikalES artikalES) {
        return ArtikalESDto.builder()
                .naziv(artikalES.getNaziv())
                .cena(artikalES.getCena())
                .opis(artikalES.getOpis())
                .build();
    }

}
