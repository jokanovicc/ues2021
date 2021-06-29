package com.ftn.Taverna.web.kontroleri;


import com.ftn.Taverna.repository.KorisnikRepository;
import com.ftn.Taverna.repository.PorudzbinaRepository;
import com.ftn.Taverna.model.*;
import com.ftn.Taverna.web.kontroleri.DTO.*;
import com.ftn.Taverna.web.kontroleri.DTO.post.KupacDTOPost;
import com.ftn.Taverna.web.kontroleri.DTO.post.ProdavacDTOPost;
import com.ftn.Taverna.servisi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/korisnici")
@CrossOrigin("*")

public class KorisniciKontroler {

    @Autowired
    private KupacServis kupacServis;
    @Autowired
    private ProdavacServis prodavacServis;
    @Autowired
    private ArtikliServis artikliServis;
    @Autowired
    private PorudzbinaServis porudzbinaServis;
    @Autowired
    private KorisnikServis korisnikServis;
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //CRUD operacije za kupca


    @GetMapping(value = "/svi-korisnici")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Collection<KorisnikDTO>> getKorisnici() {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();
        for (Korisnik k : korisnici) {
            if (!k.getRoles().equals(Roles.ADMIN)) {
                korisnikDTOS.add(new KorisnikDTO(k));
            }

        }
        return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
    }



    @GetMapping(value = "/prodavac-info")
    public ResponseEntity<ProdavacDTO> nadjiInformacijaOdProdavca(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Prodavac prodavac = prodavacServis.findByUsername(userPrincipal.getUsername());
        return new ResponseEntity<>(new ProdavacDTO(prodavac), HttpStatus.OK);


    }

    @GetMapping(value = "/korisnik-informacije/{id}")
    public ResponseEntity<KorisnikDTO> nadjiInformacijeKorisnika(@PathVariable("id") int id) {
        Korisnik korisnik = korisnikServis.findOne(id);
        return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(korisnik), HttpStatus.OK);

    }




    @PostMapping(value = "/blokiraj/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> blokiraj(@PathVariable("id") Integer id) {
        Korisnik korisnik = korisnikServis.findOne(id);
        if (korisnik.isBlokiran()) {
            korisnik.setBlokiran(false);
            korisnikServis.save(korisnik);


        } else {
            korisnik.setBlokiran(true);
            korisnikServis.save(korisnik);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping(value = "/moje-info")
    public ResponseEntity<KorisnikDTO> getMyInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Korisnik korisnik = korisnikServis.findByUsername(username);
        return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);


    }


    @PutMapping(value = "/izmena-info")
    public ResponseEntity<KorisnikDTO> izmeniKorisnika(@RequestBody KorisnikDTO korisnikDTO, Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Korisnik korisnik = korisnikServis.findByUsername(username);
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());
        korisnik.setAdresa(korisnikDTO.getAdresa());
        korisnikServis.save(korisnik);

        return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);


    }


    @PostMapping(value = "/lista-kupaca", consumes = "application/json")
    public ResponseEntity<Boolean> snimiKupca(@RequestBody KupacDTOPost kupacDTO) {

        Korisnik noviKorisnik = new Korisnik();
        Kupac noviKupac = new Kupac();

        List<String> korisnicka = korisnikRepository.findAllKorisnicko();
        if (korisnicka.contains(kupacDTO.getKorisnicko())) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

        } else {
            noviKorisnik.setKorisnicko(kupacDTO.getKorisnicko());
            noviKorisnik.setSifra(passwordEncoder.encode(kupacDTO.getSifra()));
            noviKorisnik.setIme(kupacDTO.getIme());
            noviKorisnik.setPrezime(kupacDTO.getPrezime());
            noviKorisnik.setBlokiran(false);
            noviKorisnik.setRoles(Roles.KUPAC);
            noviKorisnik.setAdresa(kupacDTO.getAdresa());
            korisnikServis.save(noviKorisnik);
            noviKupac.setKorisnik(noviKorisnik);
            noviKupac.setId(noviKorisnik.getId());
            noviKupac = kupacServis.saveKupac(noviKupac);
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);

        }
    }


    //CRUD operacije za prodavca
    @RequestMapping(value = "/lista-prodavaca", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ProdavacDTO>> findAllProdavci() {
        List<Prodavac> prodavci = prodavacServis.findAll();
        List<ProdavacDTO> prodavacDTO = new ArrayList<>();
        for (Prodavac p : prodavci) {
            if (!p.getKorisnik().isBlokiran()) {
                prodavacDTO.add(new ProdavacDTO(p));
            }
        }
        for (ProdavacDTO p1 : prodavacDTO) {
            Double ocena = porudzbinaRepository.getProsecnaOcenaProdavca(p1.getId());
            if (ocena == null) {
                ocena = 0.0;
            }
            p1.setOcena(ocena);
        }
        return new ResponseEntity<>(prodavacDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/lista-prodavaca", consumes = "application/json")
    public ResponseEntity<Boolean> snimiProdavca(@RequestBody ProdavacDTOPost prodavacDTO) {

        Korisnik noviKorisnik = new Korisnik();
        Prodavac noviProdavac = new Prodavac();


        List<String> korisnicka = korisnikRepository.findAllKorisnicko();
        List<String> imejlovi = korisnikRepository.findAllImejl();
        if (korisnicka.contains(prodavacDTO.getKorisnicko()) || imejlovi.contains(prodavacDTO.getImejl())) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        } else {


            noviKorisnik.setKorisnicko(prodavacDTO.getKorisnicko());
            noviKorisnik.setSifra(passwordEncoder.encode(prodavacDTO.getSifra()));
            noviKorisnik.setIme(prodavacDTO.getIme());
            noviKorisnik.setPrezime(prodavacDTO.getPrezime());
            noviKorisnik.setBlokiran(false);
            noviKorisnik.setRoles(Roles.PRODAVAC);

            korisnikServis.save(noviKorisnik);


            noviProdavac.setImejl(prodavacDTO.getImejl());
            noviProdavac.setKorisnik(noviKorisnik);
            noviProdavac.setId(noviKorisnik.getId());
            noviProdavac.getKorisnik().setAdresa(prodavacDTO.getAdresa());
            noviProdavac.setNaziv(prodavacDTO.getNaziv());
            noviProdavac.setPoslujeOd(Date.valueOf(LocalDate.now()));

            noviProdavac = prodavacServis.saveProdavac(noviProdavac);
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);

        }


    }
}


