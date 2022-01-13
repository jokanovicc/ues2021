package com.ftn.Taverna.elastic.services;

import com.ftn.Taverna.elastic.controllers.dtoS.ArtikalESDto;
import com.ftn.Taverna.elastic.controllers.dtoS.PorudzbinaESDto;
import com.ftn.Taverna.elastic.controllers.dtoS.SimpleQueryES;
import com.ftn.Taverna.elastic.mappers.ArtikalMapper;
import com.ftn.Taverna.elastic.mappers.PorudzbinaMapper;
import com.ftn.Taverna.elastic.model.ArtikalES;
import com.ftn.Taverna.elastic.model.PorudzbinaES;
import com.ftn.Taverna.elastic.repository.PorudzbinaEsRepository;
import com.ftn.Taverna.repository.PorudzbinaRepository;
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
public class PorudzbinaESService {

    @Autowired
    private PorudzbinaEsRepository porudzbinaEsRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public PorudzbinaESService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public List<PorudzbinaESDto> getByTekst(String tekst){
        return mapsPorudzbinaToPorudzbinaDTO(porudzbinaEsRepository.findAllByKomentar(tekst));
    }

    private List<PorudzbinaESDto> mapsPorudzbinaToPorudzbinaDTO(List<PorudzbinaES> porudzbinaESList){
        List<PorudzbinaESDto> porudzbinaESDtos = new ArrayList<>();
        for(PorudzbinaES porudzbinaES : porudzbinaESList){
            porudzbinaESDtos.add(PorudzbinaMapper.mapResponseDTO(porudzbinaES));
        }

        return porudzbinaESDtos;
    }


    public void index(PorudzbinaES porudzbinaES){
        porudzbinaEsRepository.save(porudzbinaES);
    }

    public PorudzbinaES findById(Integer id){
        return porudzbinaEsRepository.findByJpaId(id);
    }


    public List<PorudzbinaESDto> findByOcena(Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("ocena",range));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(priceQuery);

        return PorudzbinaMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<PorudzbinaESDto> findByCena(Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder priceQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("cena",range));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(priceQuery);

        return PorudzbinaMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<PorudzbinaESDto> searchByOcenaTekstAnd(String naziv, Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder ratingQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("ocena",range));
        QueryBuilder textQuery = SearchQueryGenerator.createWordQuery(new SimpleQueryES("komentar",naziv));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(ratingQuery)
                .must(textQuery);
        return PorudzbinaMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public List<PorudzbinaESDto> searchByOcenaTekstOr(String naziv, Double from, Double to){
        String range = from + "-" + to;
        QueryBuilder ratingQuery= SearchQueryGenerator.createRangeQuery(new SimpleQueryES("ocena",range));
        QueryBuilder komentarQuery = SearchQueryGenerator.createWordQuery(new SimpleQueryES("komentar",naziv));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(ratingQuery)
                .should(komentarQuery);
        return PorudzbinaMapper.mapDtos(searchByBoolQuery(boolQueryBuilder));



    }

    public Double getUkupnaCenaPorudzbine(PorudzbinaES p){
        return porudzbinaRepository.getUkupnaCenaPorudzbine(p.getJpaId());
    }




    private SearchHits<PorudzbinaES> searchByBoolQuery(BoolQueryBuilder boolQueryBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, PorudzbinaES.class,  IndexCoordinates.of("porudzbine"));
    }


}
