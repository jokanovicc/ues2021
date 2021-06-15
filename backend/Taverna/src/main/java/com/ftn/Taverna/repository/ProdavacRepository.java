package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Prodavac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdavacRepository extends JpaRepository<Prodavac, Integer> {

    Prodavac findByKorisnik_Korisnicko(String korisnicko);
}
