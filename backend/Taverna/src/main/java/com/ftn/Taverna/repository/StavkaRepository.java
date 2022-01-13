package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Porudzbina;
import com.ftn.Taverna.model.Stavka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StavkaRepository extends JpaRepository<Stavka,Integer> {

    List<Stavka> findByPorudzbina(Porudzbina porudzbina);


}
