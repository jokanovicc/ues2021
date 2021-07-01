package com.ftn.Taverna.web.kontroleri;

import com.ftn.Taverna.model.Akcija;
import com.ftn.Taverna.model.Artikal;
import com.ftn.Taverna.web.kontroleri.DTO.AkcijaDTO;
import com.ftn.Taverna.web.kontroleri.DTO.AkcijaPrikazDTO;
import com.ftn.Taverna.web.kontroleri.DTO.ArtikalDTO;
import com.ftn.Taverna.web.kontroleri.DTO.post.ArtikalDTOPost;
import com.ftn.Taverna.model.Prodavac;
import com.ftn.Taverna.servisi.AkcijaServis;
import com.ftn.Taverna.servisi.ArtikliServis;
import com.ftn.Taverna.servisi.ProdavacServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/artikli")
@CrossOrigin("*")
public class ArtikliKontroler {


    @Autowired
    private ArtikliServis artikliServis;
    @Autowired
    private ProdavacServis prodavacServis;
    @Autowired
    private AkcijaServis akcijaServis;



    //CRUD OPERACIJE ARTIKLA
    @GetMapping
    public ResponseEntity<Collection<ArtikalDTO>> findAllArtikli() {
        List<Artikal> artikli = artikliServis.findAll();
        List<ArtikalDTO> artikliDTO = new ArrayList<>();
        for(Artikal a: artikli){
            artikliDTO.add(new ArtikalDTO(a));
        }
        return new ResponseEntity<>(artikliDTO, HttpStatus.OK);


    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtikalDTO> getArtikalById(@PathVariable("id") Integer id,Authentication authentication){
        Artikal artikal = artikliServis.findOne(id);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        Prodavac prodavac = prodavacServis.findByUsername(username);
        if(prodavac!=null){
            List<Artikal> artikli = artikliServis.findByProdavac(prodavac.getId());
            if(!artikli.contains(artikal)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else {
            if (artikal == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        }
        return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.OK);
    }


    public double getSnizenaCena(Integer id,double cenaStara){
        double cena = 0.0;
        Double popust = artikliServis.procenatPopusta(id,Date.valueOf(LocalDate.now()));
        if(popust>80){
            popust = 80.0;
        }
        if(popust!=0.0) {
            cena = cenaStara - ((popust / 100) * cenaStara);
        }
        return cena;
    }




    @GetMapping(value = "/prodavac/{id}")
    public ResponseEntity<Collection<ArtikalDTO>> gerArtikliProdavca(@PathVariable("id") Integer id){
        System.out.println("Ovde sam");
        List<Artikal> artikli = artikliServis.findByProdavac(id);
        List<ArtikalDTO> artikalDTOS = new ArrayList<>();
        for (Artikal artikal:artikli) {
            if(!artikal.isObrisan())
                artikalDTOS.add(new ArtikalDTO(artikal));
                for(ArtikalDTO artikalDTO:artikalDTOS){
                    artikalDTO.setAkcijskaCena(getSnizenaCena(artikalDTO.getId(),artikalDTO.getCena()));
                }

        }

        return new ResponseEntity<>(artikalDTOS, HttpStatus.OK);


    }


    @GetMapping(value = "/prodavac-artikli")
    @PreAuthorize("hasAnyRole('PRODAVAC')")
    public ResponseEntity<Collection<ArtikalDTO>> getArtikalByProdavac(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Prodavac prodavac = prodavacServis.findByUsername(username);
        List<Artikal> artikli = artikliServis.findByProdavac(prodavac.getId());
        List<ArtikalDTO> artikalDTOS = new ArrayList<>();
        for (Artikal artikal:artikli) {
            if(!artikal.isObrisan())
                artikalDTOS.add(new ArtikalDTO(artikal));

        }

        return new ResponseEntity<>(artikalDTOS, HttpStatus.OK);


    }


    @PostMapping
    @PreAuthorize("hasAnyRole('PRODAVAC')")
    public ResponseEntity<ArtikalDTO> snimiArtikal(@RequestBody ArtikalDTOPost artikalDTO, Authentication authentication){
        Artikal artikal = new Artikal();
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        System.out.println("Ovo jeee" + username);
        Prodavac prodavac = prodavacServis.findByUsername(username);
        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setCena(artikalDTO.getCena());
        artikal.setOpis(artikalDTO.getOpis());
        artikal.setPhoto(artikalDTO.getPhoto());
        artikal.setObrisan(false);
        if(prodavac==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        artikal.setProdavac(prodavac);
        artikal = artikliServis.saveArtikal(artikal);
        return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.CREATED);


    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PRODAVAC')")
    public ResponseEntity<ArtikalDTO> izmeniArtikal(@RequestBody ArtikalDTOPost artikalDTO,@PathVariable("id") Integer id,Authentication authentication){
        Artikal artikal = artikliServis.findOne(id);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Prodavac prodavac = prodavacServis.findByUsername(userPrincipal.getUsername());
        List<Artikal> artikli = artikliServis.findByProdavac(prodavac.getId());
        if(!artikli.contains(artikal)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            artikal.setNaziv(artikalDTO.getNaziv());
            artikal.setCena(artikalDTO.getCena());
            artikal.setOpis(artikalDTO.getOpis());
            artikliServis.saveArtikal(artikal);
            return new ResponseEntity<>(new ArtikalDTO(artikal), HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('PRODAVAC')")
    public ResponseEntity<Void> obrisiArtikal(@PathVariable("id") Integer id,Authentication authentication){
        Artikal artikal = artikliServis.findOne(id);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Prodavac prodavac = prodavacServis.findByUsername(userPrincipal.getUsername());
        List<Artikal> artikli = artikliServis.findByProdavac(prodavac.getId());
        if(!artikli.contains(artikal)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            if (artikal != null) {
                artikal.setObrisan(true);
                artikliServis.saveArtikal(artikal);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

            }
        }
    }

    @PostMapping(value = "/akcije")
    @PreAuthorize("hasAnyRole('PRODAVAC')")
    public ResponseEntity<Void> napraviAkciju(@RequestBody AkcijaDTO akcijaDTO,Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Prodavac prodavac = prodavacServis.findByUsername(userPrincipal.getUsername());
        Akcija akcija = new Akcija();
        System.out.println("to je " +akcijaDTO.getDoKad());
        akcija.setDoKad(akcijaDTO.getDoKad());
        akcija.setOdKad(akcijaDTO.getOdKad());
        akcija.setProcenat(akcijaDTO.getPopust());
        akcija.setTekst(akcijaDTO.getOpis());
        akcija.setProdavac(prodavac);
        for(Integer i: akcijaDTO.getArtikli()){
            Artikal artikal = artikliServis.findOne(i);
            akcija.getArtikli().add(artikal);
        }
        akcijaServis.saveAkcija(akcija);
        return new ResponseEntity<Void>(HttpStatus.OK);


    }


    @GetMapping("/akcija_prodavca")
    public ResponseEntity<Collection<AkcijaPrikazDTO>> getAkcijeProdavca(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Prodavac prodavac = prodavacServis.findByUsername(userPrincipal.getUsername());
        List<Akcija> akcije = akcijaServis.akcijeProdavca(prodavac.getId());
        ArrayList<AkcijaPrikazDTO> prikazAkcije = new ArrayList<>();
        for(Akcija a: akcije){
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            if(date.before(a.getDoKad())) {
                prikazAkcije.add(new AkcijaPrikazDTO(a));
                for (AkcijaPrikazDTO akcijaPrikazDTO : prikazAkcije) {
                    List<String> stringovi = akcijaServis.artikliNaAkcijiPrikaz(akcijaPrikazDTO.getId());
                    String artikli = String.join(" | ", stringovi);
                    akcijaPrikazDTO.setArtikli(artikli);
                }
            }

        }

        return new ResponseEntity<>(prikazAkcije, HttpStatus.OK);


    }






}
