package com.ftn.Taverna.elastic.services;

import com.ftn.Taverna.elastic.controllers.dtoS.ArtikalESDto;
import com.ftn.Taverna.elastic.controllers.dtoS.PorudzbinaESDto;
import com.ftn.Taverna.elastic.controllers.dtoS.SimpleQueryES;
import com.ftn.Taverna.elastic.mappers.ArtikalMapper;
import com.ftn.Taverna.elastic.mappers.PorudzbinaMapper;
import com.ftn.Taverna.elastic.model.ArtikalES;
import com.ftn.Taverna.elastic.repository.ArtikalEsRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtikalESService {


    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ArtikalEsRepository artikalEsRepository;


    public ArtikalESService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }


    public List<ArtikalESDto> findByNaziv(String naziv) {
        return mapsArtikalToArtikalDTO(artikalEsRepository.findAllByNaziv(naziv));
    }


    private List<ArtikalESDto> mapsArtikalToArtikalDTO(List<ArtikalES> artikalESList) {
        List<ArtikalESDto> artikalESDtos = new ArrayList<>();
        for (ArtikalES artikalES : artikalESList) {
            artikalESDtos.add(ArtikalMapper.mapResponseDto(artikalES));
        }
        return artikalESDtos;
    }



    public List<ArtikalESDto> findByPrice(Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("cena",range));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(priceQuery);

        return ArtikalMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    private SearchHits<ArtikalES> searchByBoolQuery(BoolQueryBuilder boolQueryBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, ArtikalES.class,  IndexCoordinates.of("artikli"));
    }



    public List<ArtikalESDto> searchByNazivPriceAnd(String naziv, Double from, Double to){
            String range = from + "-" + to;
            QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("cena",range));
            QueryBuilder komentarQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryES("naziv",naziv));
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                        .must(priceQuery)
                        .must(komentarQuery);
            return ArtikalMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<ArtikalESDto> searchByNazivPriceOr(String naziv, Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("cena",range));
        QueryBuilder komentarQuery = SearchQueryGenerator.createWordQuery(new SimpleQueryES("naziv",naziv));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(priceQuery)
                .should(komentarQuery);
        return ArtikalMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }


}
