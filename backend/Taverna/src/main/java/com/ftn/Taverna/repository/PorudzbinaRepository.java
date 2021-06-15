package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {

    List<Porudzbina> findPorudzbinaByKupac_Id(Integer id);


//    SELECT p.id,p.anoniman_komentar,p.arhiviran_komentar,p.dostavljeno,p.komentar,p.ocena,p.satnica,p.kupac_korisnik_id from porudzbina p left join stavka s on p.id = s.porudzbina_id left join artikal a on a.id = s.artikal_id left join prodavac pr on a.prodavac_korisnik_id = pr.korisnik_id where pr.korisnik_id = 3
//    group by p.id,p.anoniman_komentar,p.arhiviran_komentar,p.dostavljeno,p.komentar,p.ocena,p.satnica,p.kupac_korisnik_id

    @Query("SELECT s.kolicina,a.naziv From Porudzbina pdz left join Stavka s on s.porudzbina.id = pdz.id left join Artikal  a on s.artikal.id = a.id left join Prodavac  p on a.prodavac.id=p.id where pdz.id = ?1")
    List<String> getNaziviArtikala(Integer id);

    @Query("SELECT a.prodavac.id FROM Porudzbina pdz left join Stavka s on s.porudzbina.id = pdz.id left join Artikal a on s.artikal.id = a.id where pdz.id=?1 group by a.prodavac.id")
    Integer getProdavac(Integer id);

    @Query("SELECT avg(p.ocena) from Porudzbina p inner join Stavka s on s.porudzbina.id = p.id inner join Artikal a on s.artikal.id = a.id where a.prodavac.id = ?1")
    Double getProsecnaOcenaProdavca(Integer id);

    @Query(value="select pdz.id,pdz.anoniman_komentar,pdz.arhiviran_komentar,pdz.dostavljeno,pdz.komentar,pdz.ocena,pdz.satnica,pdz.komentar,pdz.kupac_korisnik_id FROM porudzbina pdz where pdz.id in (select s.porudzbina_id from stavka s where s.artikal_id in (select a.id from artikal a where a.prodavac_korisnik_id =?1))", nativeQuery = true)
    List<Porudzbina> getKomentari(Integer id);

}
