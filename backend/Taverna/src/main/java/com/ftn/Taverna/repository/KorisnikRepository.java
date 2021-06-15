package com.ftn.Taverna.repository;

import com.ftn.Taverna.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Korisnik findFirstByKorisnickoAndSifra(String korisnicko, String sifra);

    @Query("SELECT k.korisnicko from Korisnik k")
    List<String> findAllKorisnicko();

    @Query("SELECT p.imejl from Prodavac p")
    List<String> findAllImejl();


    Optional<Korisnik> findFirstByKorisnicko(String username);
}
