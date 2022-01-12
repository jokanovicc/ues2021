package com.ftn.Taverna.elastic.repository;

import com.ftn.Taverna.elastic.model.PorudzbinaES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PorudzbinaEsRepository extends ElasticsearchRepository<PorudzbinaES, String> {


    List<PorudzbinaES> findAllByKomentar(String tekst);




}
