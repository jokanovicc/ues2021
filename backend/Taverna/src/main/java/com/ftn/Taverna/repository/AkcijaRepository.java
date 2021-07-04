package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Akcija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AkcijaRepository extends JpaRepository<Akcija, Integer> {

    @Query(" from Akcija a where a.prodavac.korisnik.id =?1")
    List<Akcija> getAkcijeProdavca(Integer id);

    @Query(value = "select a.naziv from artikli_akcije aa inner join artikal a on aa.artikal_id = a.id and aa.akcija_id=?1",nativeQuery = true)
    List<String> artikliAkcijaPrikaz(Integer id);

    List<Akcija> getAkcijaByProdavac_Korisnik_Id(Integer id);
}
