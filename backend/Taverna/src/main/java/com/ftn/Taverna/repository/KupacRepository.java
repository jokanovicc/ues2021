package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KupacRepository extends JpaRepository<Kupac, Integer> {

    Kupac findByKorisnik_Korisnicko(String korisnicko);
}
