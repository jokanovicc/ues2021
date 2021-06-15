package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtikalRepository extends JpaRepository<Artikal, Integer> {

    List<Artikal> findByProdavac_Id(Integer id);

}
