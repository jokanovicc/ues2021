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

    @RequestMapping(value = "/lista-kupaca", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<KupacDTOPost>> findAllKupac() {
        List<Kupac> kupci = kupacServis.findAll();
        List<KupacDTOPost> kupciDTO = new ArrayList<>();
        for(Kupac k: kupci){
            kupciDTO.add(new KupacDTOPost(k));
        }
        return new ResponseEntity<>(kupciDTO, HttpStatus.OK);


    }

    @GetMapping(value = "/svi-korisnici")
    public ResponseEntity<Collection<KorisnikDTO>> getKorisnici(){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();
        for (Korisnik k:korisnici) {
            if(!k.getRoles().equals(Roles.ADMIN)){
                korisnikDTOS.add(new KorisnikDTO(k));
            }

        }
        return new ResponseEntity<>(korisnikDTOS,HttpStatus.OK);
    }

    @PutMapping("izmena-sifre")
    private ResponseEntity<Boolean> izmeniSifru(@RequestBody IzmenaSifreDTO izmenaSifreDTO, Authentication authentication){
        System.out.println("STIGAO");
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Korisnik korisnik = korisnikServis.findByUsername(userPrincipal.getUsername());
        boolean podudaravanje = BCrypt.checkpw(izmenaSifreDTO.getStaraSifra(),korisnik.getSifra());
        if(!podudaravanje){
            return new ResponseEntity<>(false,HttpStatus.OK);


        }
        korisnik.setSifra(passwordEncoder.encode(izmenaSifreDTO.getNovaSifra()));
        korisnikServis.save(korisnik);
        return new ResponseEntity<>(true,HttpStatus.OK);


    }


    @GetMapping(value = "/prodavac-info")
    public ResponseEntity<ProdavacDTO> nadjiInformacijaOdProdavca(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Prodavac prodavac = prodavacServis.findByUsername(userPrincipal.getUsername());
        return new ResponseEntity<>(new ProdavacDTO(prodavac),HttpStatus.OK);


    }

    @GetMapping(value = "/korisnik-informacije/{id}")
    public ResponseEntity<KorisnikDTO> nadjiInformacijeKorisnika(@PathVariable("id") int id){
        Korisnik korisnik = korisnikServis.findOne(id);
        return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(korisnik),HttpStatus.OK);

    }


    @GetMapping("/usernamovi")
    public ResponseEntity<Collection<String>> getSvaKorisnicka(){
        return new ResponseEntity<>(korisnikRepository.findAllKorisnicko(),HttpStatus.OK);
    }

    @GetMapping("/imejlovi")
    public ResponseEntity<Collection<String>> getImejloviSvi(){
        return new ResponseEntity<>(korisnikRepository.findAllImejl(),HttpStatus.OK);
    }



    @PostMapping(value = "/blokiraj/{id}")
    public ResponseEntity<Void> blokiraj(@PathVariable("id") Integer id){
        Korisnik korisnik = korisnikServis.findOne(id);
        if(korisnik.isBlokiran()){
            korisnik.setBlokiran(false);
            korisnikServis.save(korisnik);


        }else {
            korisnik.setBlokiran(true);
            korisnikServis.save(korisnik);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }



    @GetMapping(value = "/moje-info")
    public ResponseEntity<KorisnikDTO> getMyInfo(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Korisnik korisnik = korisnikServis.findByUsername(username);
        return new ResponseEntity<>(new KorisnikDTO(korisnik),HttpStatus.OK);



    }



    @PutMapping(value = "/izmena-info")
    public ResponseEntity<KorisnikDTO> izmeniKorisnika(@RequestBody KorisnikDTO korisnikDTO,Authentication authentication){

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Korisnik korisnik = korisnikServis.findByUsername(username);
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());
        korisnik.setAdresa(korisnikDTO.getAdresa());
        korisnikServis.save(korisnik);

        return new ResponseEntity<>(new KorisnikDTO(korisnik),HttpStatus.OK);


    }




    @GetMapping(value = "/lista-kupaca/{id}")
    public ResponseEntity<KupacDTO> getKupacById(@PathVariable("id") Integer id){
            Kupac kupac = kupacServis.findOne(id);
            if(kupac == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new KupacDTO(kupac), HttpStatus.OK);
        }



    @PostMapping(value ="/lista-kupaca", consumes = "application/json")
    public ResponseEntity<KupacDTO> snimiKupca(@RequestBody KupacDTOPost kupacDTO){

        Korisnik noviKorisnik = new Korisnik();
        Kupac noviKupac = new Kupac();


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
        return new ResponseEntity<KupacDTO>(new KupacDTO(noviKupac), HttpStatus.CREATED);

    }



    @PutMapping(value ="/lista-kupaca",consumes = "application/json")
    public ResponseEntity<KupacDTOPost> izmeniKupca(@RequestBody KupacDTOPost kupacDTO){
        Kupac kupac = kupacServis.findOne(kupacDTO.getId());
        if(kupac == null){
            return new ResponseEntity<KupacDTOPost>(HttpStatus.BAD_REQUEST);
        }

        kupac.getKorisnik().setKorisnicko(kupacDTO.getKorisnicko());
        kupac.getKorisnik().setSifra(kupacDTO.getSifra());
        kupac.getKorisnik().setIme(kupacDTO.getIme());
        kupac.getKorisnik().setPrezime(kupacDTO.getPrezime());
        kupac.getKorisnik().setAdresa(kupacDTO.getAdresa());


        kupacServis.saveKupac(kupac);
        return new ResponseEntity<KupacDTOPost>(new KupacDTOPost(kupac), HttpStatus.CREATED);



    }


    @DeleteMapping(value = "/lista-kupaca/{id}")
    public ResponseEntity<Void> obrisiKupca(@PathVariable("id") Integer id){
        Kupac kupac = kupacServis.findOne(id);
        if(kupac!=null){
            kupacServis.deleteKupac(kupac);
            return new ResponseEntity<Void>(HttpStatus.OK);

        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        }
    }


    //CRUD operacije za prodavca

    @RequestMapping(value = "/lista-prodavaca", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ProdavacDTO>> findAllProdavci() {
        List<Prodavac> prodavci = prodavacServis.findAll();
        List<ProdavacDTO> prodavacDTO = new ArrayList<>();
        for(Prodavac p:prodavci){
            if(!p.getKorisnik().isBlokiran()) {
                prodavacDTO.add(new ProdavacDTO(p));
            }
        }
        for(ProdavacDTO p1: prodavacDTO){
            Double ocena = porudzbinaRepository.getProsecnaOcenaProdavca(p1.getId());
            if(ocena==null) {
                ocena = 0.0;
            }
            p1.setOcena(ocena);
        }
        return new ResponseEntity<>(prodavacDTO, HttpStatus.OK);
    }



    @GetMapping(value = "/lista-prodavaca/{id}")
    public ResponseEntity<ProdavacDTO> getProdavacById(@PathVariable("id") Integer id){
        Prodavac prodavac = prodavacServis.findOne(id);
        if(prodavac == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(new ProdavacDTO(prodavac), HttpStatus.OK);
    }




    @PostMapping(value ="/lista-prodavaca", consumes = "application/json")
    public ResponseEntity<ProdavacDTOPost> snimiProdavca(@RequestBody ProdavacDTOPost prodavacDTO){

        Korisnik noviKorisnik = new Korisnik();
        Prodavac noviProdavac = new Prodavac();


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
        return new ResponseEntity<ProdavacDTOPost>(new ProdavacDTOPost(noviProdavac), HttpStatus.CREATED);

    }



    @PutMapping(value ="/lista-prodavaca",consumes = "application/json")
    public ResponseEntity<ProdavacDTOPost> izmeniProdavca(@RequestBody ProdavacDTOPost prodavacDTO){
        Prodavac prodavac = prodavacServis.findOne(prodavacDTO.getId());
        if(prodavac == null){
            return new ResponseEntity<ProdavacDTOPost>(HttpStatus.BAD_REQUEST);
        }
        prodavac.getKorisnik().setIme(prodavacDTO.getIme());
        prodavac.getKorisnik().setPrezime(prodavacDTO.getPrezime());
        prodavac.getKorisnik().setAdresa(prodavacDTO.getAdresa());
        prodavac.getKorisnik().setKorisnicko(prodavacDTO.getKorisnicko());
        prodavac.getKorisnik().setSifra(prodavacDTO.getSifra());

        prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
        prodavac.setImejl(prodavacDTO.getImejl());
        prodavac.setNaziv(prodavacDTO.getNaziv());

        prodavacServis.saveProdavac(prodavac);
        return new ResponseEntity<ProdavacDTOPost>(new ProdavacDTOPost(prodavac), HttpStatus.CREATED);



    }

    @DeleteMapping(value = "/lista-prodavaca/{id}")
    public ResponseEntity<Void> obrisiProdavca(@PathVariable("id") Integer id){
        Prodavac prodavac = prodavacServis.findOne(id);
        if(prodavac!=null){
            prodavacServis.deleteProdavac(prodavac);
            return new ResponseEntity<Void>(HttpStatus.OK);

        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        }
    }


    //FIND Artikli od prodavca naprimer
    @GetMapping(value = "/lista-prodavaca/{id}/lista-artikala")
    public ResponseEntity<Collection<ArtikalDTO>> findArtikleByProdavac(@PathVariable("id") Integer id){
        List<Artikal> artikli = artikliServis.findByProdavac(id);
        List<ArtikalDTO> artikliDTO = new ArrayList<>();
        for(Artikal a: artikli){
            artikliDTO.add(new ArtikalDTO(a));
        }
        return new ResponseEntity<>(artikliDTO,HttpStatus.OK);
    }







    //sve porudzbine kupca
    @GetMapping(value = "/lista-kupaca/{id}/lista-porudzbina")
    public ResponseEntity<Collection<PorudzbinaDTO>> findPorudzbineByKupac(@PathVariable("id") Integer id){
        List<Porudzbina> porudzbine = porudzbinaServis.findByKupacId(id);
        List<PorudzbinaDTO> porudzbinaDTO = new ArrayList<>();
        for(Porudzbina p : porudzbine){
            porudzbinaDTO.add(new PorudzbinaDTO(p));
        }

        return new ResponseEntity<>(porudzbinaDTO, HttpStatus.OK);


    }


}


