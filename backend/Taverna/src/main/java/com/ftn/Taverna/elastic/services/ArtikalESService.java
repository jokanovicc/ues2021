package com.ftn.Taverna.elastic.services;

import com.ftn.Taverna.elastic.controllers.dtoS.ArtikalESDto;
import com.ftn.Taverna.elastic.controllers.dtoS.SimpleQueryES;
import com.ftn.Taverna.elastic.mappers.ArtikalMapper;
import com.ftn.Taverna.elastic.model.ArtikalES;
import com.ftn.Taverna.elastic.repository.ArtikalEsRepository;
import com.ftn.Taverna.model.Artikal;
import com.ftn.Taverna.repository.ArtikalRepository;
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

    @Autowired
    private ArtikalRepository artikalRepository;


    public ArtikalESService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public void index(ArtikalES a){artikalEsRepository.save(a);
    }
    public ArtikalES findByJpaID(Integer jpaId){
        return artikalEsRepository.getByJpaId(jpaId);
    }


    public List<ArtikalES> findByNaziv(String naziv) {
        return artikalEsRepository.findAllByNaziv(naziv);
    }


    private List<ArtikalESDto> mapsArtikalToArtikalDTO(List<ArtikalES> artikalESList) {
        List<ArtikalESDto> artikalESDtos = new ArrayList<>();
        for (ArtikalES artikalES : artikalESList) {
            artikalESDtos.add(ArtikalMapper.mapResponseDto(artikalES));
        }
        return artikalESDtos;
    }

    public Double getRating(ArtikalES artikalES) {
        if(artikalRepository.getRatingArtikla(artikalES.getJpaId()) != null){
            return artikalRepository.getRatingArtikla(artikalES.getJpaId());
        }else return 0.0;

    }

    public Integer getBrojKomentara(ArtikalES artikalES){
        return artikalRepository.getBrojKomentaraArtikla(artikalES.getJpaId());
    }





    public List<ArtikalESDto> findByPrice(Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("cena",range));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(priceQuery);

        return ArtikalMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<ArtikalESDto> findByOcena(Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("rating",range));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(priceQuery);

        return ArtikalMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<ArtikalESDto> findByKomentara(Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("komentara",range));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(priceQuery);

        return ArtikalMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<ArtikalESDto> findByRatingQuery(Double from, Double to){
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
            QueryBuilder komentarQuery = SearchQueryGenerator.createWordQuery(new SimpleQueryES("naziv",naziv));
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
