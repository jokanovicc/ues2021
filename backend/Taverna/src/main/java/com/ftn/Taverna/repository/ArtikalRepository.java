package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ArtikalRepository extends JpaRepository<Artikal, Integer> {

    List<Artikal> findByProdavac_Id(Integer id);

    @Query(value = "select sum(a.procenat) from akcija a where a.id in (select aa.akcija_id  from artikli_akcije aa where aa.artikal_id = ?1) and  a.do_kad >= ?2 and a.od_kad <= ?2",nativeQuery = true)
    Double getProcenatAkcije(Integer id,Date datum);

    @Query(value = "select avg(p.ocena) from porudzbina p where p.id in (select s.porudzbina_id from stavka s where s.artikal_id = ?1)", nativeQuery = true)
    Double getRatingArtikla(Integer artikalId);

    @Query(value = "select count(komentar) from porudzbina p where p.id in (select porudzbina_id from stavka where artikal_id = ?1) and komentar is not null", nativeQuery = true)
    Integer getBrojKomentaraArtikla(Integer artikalId);


}
