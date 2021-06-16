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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    ArtikalRepository artikalRepository;

    @Autowired
    StavkaRepository stavkaRepository;
    @Autowired
    KorisnikServis korisnikServis;

    @GetMapping
    public ResponseEntity<Collection<PorudzbinaDTO>> findAllPorudzbine() {
        List<Porudzbina> porudzbine = porudzbinaServis.findAll();
        List<PorudzbinaDTO> porudzbineDTO = new ArrayList<>();
        for(Porudzbina p:porudzbine){
            porudzbineDTO.add(new PorudzbinaDTO(p));
        }
        return new ResponseEntity<>(porudzbineDTO, HttpStatus.OK);
    }





    @GetMapping(value = "/{id}")
    public ResponseEntity<PorudzbinaDTO> getPorudzbinaById(@PathVariable("id") Integer id){
        Porudzbina porudzbina = porudzbinaServis.findOne(id);
        if(porudzbina == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
    }


    @GetMapping(value = "/porudzbine-korisnika")
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
                    List<String> stringove = porudzbinaRepository.getNaziviArtikala(porudzbinaDTO.getId());
                    String jela = String.join(" | ", stringove);
                    porudzbinaDTO.setArtikli(jela);
                }

        }



        return new ResponseEntity<>(porudzbinaDTOS, HttpStatus.OK);


    }

    @GetMapping("prosecna-ocena/{id}")
    public ResponseEntity<Double> getOcenaProdavca(@PathVariable("id") Integer id){
        Double ocena = porudzbinaRepository.getProsecnaOcenaProdavca(id);
        if(ocena==null){
            ocena = 0.0;
        }

        return new ResponseEntity<>(ocena, HttpStatus.OK);

    }


    @PostMapping(value = "/porucivanje")
    public ResponseEntity<PorudzbinaDTO2> napraviPorudzbinu(@RequestBody PorucivanjeDTO porucivanjeDTO,Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        Kupac kupac = kupacServis.findByUsername(username);
        ArrayList<Stavka> stavkas = new ArrayList<>();
        System.out.println(porucivanjeDTO);
        Porudzbina porudzbina = new Porudzbina();
        Random r = new Random();
        int low = 10;
        int high = 500;
        int result = r.nextInt(high-low) + low;
        porudzbina.setId(result);
        porudzbina.setSatnica(Date.valueOf(LocalDate.now()));
        porudzbina.setKupac(kupac);
        porudzbina.setDostavljeno(false);
        porudzbina.setAnonimanKomentar(false);
        porudzbina.setAnonimanKomentar(false);
        porudzbinaRepository.save(porudzbina);
        for (StavkaDTO stavkaDTO:porucivanjeDTO.getListaStavki()) {


                Stavka stavka = new Stavka();
                stavka.setKolicina(stavkaDTO.getKolicina());
                stavka.setArtikal(artikliServis.findOne(stavkaDTO.getArtikalId()));
                stavka.setPorudzbina(porudzbina);
                stavkas.add(stavka);
                stavkaRepository.save(stavka);

        }
        porudzbina.setStavke(stavkas);
        porudzbinaRepository.save(porudzbina);

        return new ResponseEntity<>(new PorudzbinaDTO2(porudzbina),HttpStatus.OK);






    }


    @GetMapping("/stiglo/{id}")
    public ResponseEntity<Void> stiglaPorudzbina(@PathVariable("id") Integer id){
        Porudzbina porudzbina = porudzbinaServis.findOne(id);
        porudzbina.setDostavljeno(true);
        porudzbinaServis.save(porudzbina);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping("komentar")
    public ResponseEntity<KomentarDTO> dodavanjeKomentara(@RequestBody KomentarPOSTDTO komentarDTO){
        Porudzbina porudzbina = porudzbinaServis.findOne(komentarDTO.getPorudzbina());
        porudzbina.setKomentar(komentarDTO.getKomentar());
        porudzbina.setAnonimanKomentar(komentarDTO.isAnoniman());
        porudzbina.setArhiviranKomentar(false);
        porudzbina.setOcena(komentarDTO.getOcena());
        porudzbinaRepository.save(porudzbina);

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
    public ResponseEntity<Void> arhivirajKomentar(@PathVariable("id") Integer id){

        Porudzbina komentar = porudzbinaServis.findOne(id);
        assert komentar != null;
        komentar.setArhiviranKomentar(true);
        porudzbinaServis.save(komentar);
        return new ResponseEntity<>(HttpStatus.OK);


    }



}


