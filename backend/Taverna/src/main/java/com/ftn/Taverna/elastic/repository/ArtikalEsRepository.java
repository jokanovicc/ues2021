package com.ftn.Taverna.elastic.repository;

import com.ftn.Taverna.elastic.model.ArtikalES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtikalEsRepository extends ElasticsearchRepository<ArtikalES,String> {

    List<ArtikalES> findAllByJpaId(Integer id);
    List<ArtikalES> findAllByNaziv(String naziv);

    ArtikalES getByJpaId(Integer id);
}
