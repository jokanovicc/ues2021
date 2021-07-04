package com.ftn.Taverna.web.kontroleri;


import com.ftn.Taverna.repository.ArtikalRepository;
import com.ftn.Taverna.repository.PorudzbinaRepository;
import com.ftn.Taverna.repository.StavkaRepository;
import com.ftn.Taverna.model.*;
import com.ftn.Taverna.servisi.*;
import com.ftn.Taverna.web.kontroleri.DTO.KomentarDTO;
import com.ftn.Taverna.web.kontroleri.DTO.PorudzbinaDTO;
import com.ftn.Taverna.web.kontroleri.DTO.PorudzbinaDTO2;
import com.ftn.Taverna.web.kontroleri.DTO.porudzbine.PorucivanjeDTO;
import com.ftn.Taverna.web.kontroleri.DTO.porudzbine.StavkaDTO;
import com.ftn.Taverna.web.kontroleri.DTO.post.KomentarPOSTDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/porudzbine")
@CrossOrigin("*")
public class PorudzbineKontroler {

    @Autowired
    private PorudzbinaServis porudzbinaServis;

    @Autowired
    private KupacServis kupacServis;

    @Autowired
    ProdavacServis prodavacServis;

    @Autowired
    ArtikliServis artikliServis;
    @Autowired
    StavkaRepository stavkaRepository;
    @Autowired
    KorisnikServis korisnikServis;



    @GetMapping(value = "/porudzbine-korisnika")
    @PreAuthorize("hasAnyRole('KUPAC')")
    public ResponseEntity<Collection<PorudzbinaDTO>> getPorudzbineKupca(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Korisnik korisnik = korisnikServis.findByUsername(username);
        List<Porudzbina> porudzbine = porudzbinaServis.findByKupacId(korisnik.getId());
        List<PorudzbinaDTO> porudzbinaDTOS = new ArrayList<>();
        for (Porudzbina p: porudzbine) {
                if(p.getKomentar()==null)
                porudzbinaDTOS.add(new PorudzbinaDTO(p));
                for (PorudzbinaDTO porudzbinaDTO : porudzbinaDTOS) {
                    List<String> stringove = porudzbinaServis.getNaziviArtikala(porudzbinaDTO.getId());
                    String jela = String.join(" | ", stringove);
                    porudzbinaDTO.setArtikli(jela);
                }

        }



        return new ResponseEntity<>(porudzbinaDTOS, HttpStatus.OK);


    }

    @GetMapping("prosecna-ocena/{id}")
    public ResponseEntity<Double> getOcenaProdavca(@PathVariable("id") Integer id){
        Double ocena = porudzbinaServis.getProsecnaOcenaProdavca(id);
        if(ocena==null){
            ocena = 0.0;
        }

        return new ResponseEntity<>(ocena, HttpStatus.OK);

    }


    @PostMapping(value = "/porucivanje")
    @PreAuthorize("hasAnyRole('KUPAC')")
    public ResponseEntity<PorudzbinaDTO2> napraviPorudzbinu(@RequestBody @Validated PorucivanjeDTO porucivanjeDTO, Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        Kupac kupac = kupacServis.findByUsername(username);
        ArrayList<Stavka> stavkas = new ArrayList<>();
        System.out.println(porucivanjeDTO);
        Porudzbina porudzbina = new Porudzbina();
        Random r = new Random();
        int low = 10;
        int high = 3000;
        int result = r.nextInt(high-low) + low;
        porudzbina.setId(result);
        porudzbina.setSatnica(Date.valueOf(LocalDate.now()));
        porudzbina.setKupac(kupac);
        porudzbina.setDostavljeno(false);
        porudzbina.setAnonimanKomentar(false);
        porudzbina.setAnonimanKomentar(false);
        porudzbinaServis.save(porudzbina);
        for (StavkaDTO stavkaDTO:porucivanjeDTO.getListaStavki()) {

                Stavka stavka = new Stavka();
                stavka.setKolicina(stavkaDTO.getKolicina());
                stavka.setArtikal(artikliServis.findOne(stavkaDTO.getArtikalId()));
                stavka.setPorudzbina(porudzbina);
                stavkas.add(stavka);
                stavkaRepository.save(stavka);

        }
        porudzbina.setStavke(stavkas);
        porudzbinaServis.save(porudzbina);

        return new ResponseEntity<>(new PorudzbinaDTO2(porudzbina),HttpStatus.OK);






    }


    @GetMapping("/stiglo/{id}")
    @PreAuthorize("hasAnyRole('KUPAC')")
    public ResponseEntity<Void> stiglaPorudzbina(@PathVariable("id") Integer id){
        Porudzbina porudzbina = porudzbinaServis.findOne(id);
        porudzbina.setDostavljeno(true);
        porudzbinaServis.save(porudzbina);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("komentar")
    @PreAuthorize("hasAnyRole('KUPAC')")
    public ResponseEntity<KomentarDTO> dodavanjeKomentara(@RequestBody @Validated KomentarPOSTDTO komentarDTO){
        Porudzbina porudzbina = porudzbinaServis.findOne(komentarDTO.getPorudzbina());
        porudzbina.setKomentar(komentarDTO.getKomentar());
        porudzbina.setAnonimanKomentar(komentarDTO.isAnoniman());
        porudzbina.setArhiviranKomentar(false);
        porudzbina.setOcena(komentarDTO.getOcena());
        porudzbinaServis.save(porudzbina);

        //ovde
        return new ResponseEntity<>(new KomentarDTO(porudzbina),HttpStatus.OK);
    }




    @GetMapping("/komentari/{id}")
    public ResponseEntity<Collection<KomentarDTO>> getKomentariProdavca(@PathVariable("id") Integer id){

        List<Porudzbina> porudzbine = porudzbinaServis.getKomentari(id);
        List<KomentarDTO> komentarDTOS = new ArrayList<>();
        for(Porudzbina p:porudzbine){
            if(!p.isArhiviranKomentar() && p.getKomentar() != null){
                komentarDTOS.add(new KomentarDTO(p));
            }
        }


        return new ResponseEntity<>(komentarDTOS, HttpStatus.OK);


        }


    @PostMapping("/komentar-arhiviraj/{id}")
    @PreAuthorize("hasAnyRole('PRODAVAC')")
    public ResponseEntity<Void> arhivirajKomentar(@PathVariable("id") Integer id){

        Porudzbina komentar = porudzbinaServis.findOne(id);
        assert komentar != null;
        komentar.setArhiviranKomentar(true);
        porudzbinaServis.save(komentar);
        return new ResponseEntity<>(HttpStatus.OK);


    }



}


