package com.ftn.Taverna.elastic.controllers;

import com.ftn.Taverna.elastic.controllers.dtoS.*;
import com.ftn.Taverna.elastic.services.ArtikalESService;
import com.ftn.Taverna.elastic.services.PorudzbinaESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elastic")
public class SearchController {


    @Autowired
    private ArtikalESService artikalESService;

    @Autowired
    private PorudzbinaESService porudzbinaESService;


    @PostMapping("/naziv")
    public List<ArtikalESDto> searchByNaziv(@RequestBody TextRequestDTO textRequestDTO){
        return artikalESService.findByNaziv(textRequestDTO.getText());
    }

    @PostMapping("/tekst-komentara")
    public List<PorudzbinaESDto> searchByTextKomentara(@RequestBody TextRequestDTO porudzbinaKomentarRequest){
        return porudzbinaESService.getByTekst(porudzbinaKomentarRequest.getText());
    }

    @GetMapping("/price")
    public List<ArtikalESDto> searchByPrice(@RequestBody ToFromRequestDTO toFromRequestDTO){
        return artikalESService.findByPrice(toFromRequestDTO.getFrom(), toFromRequestDTO.getTo());
    }

    @GetMapping("/rating")
    public List<PorudzbinaESDto> searchByRating(@RequestBody ToFromRequestDTO toFromRequestDTO){
        return porudzbinaESService.findByOcena(toFromRequestDTO.getFrom(), toFromRequestDTO.getTo());
    }


    @GetMapping("/naziv-cena")
    public List<ArtikalESDto> findByNazivCena(@RequestBody AndOrRequest artikalNazivCenaRequest){
        if(artikalNazivCenaRequest.isOrUpit() == true){
            //Upit je sa Or
            return artikalESService.searchByNazivPrice(artikalNazivCenaRequest.getNaziv(),artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo(),true);
        }else//Upit je sa And
            return artikalESService.searchByNazivPrice(artikalNazivCenaRequest.getNaziv(),artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo(),false);


    }

    @GetMapping("/komentar-ocena")
    public List<PorudzbinaESDto> findByTekstRating(@RequestBody AndOrRequest artikalNazivCenaRequest){
        if(artikalNazivCenaRequest.isOrUpit() == true){
            //Upit je sa Or
            return porudzbinaESService.findByOcenaTekst(artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo(),artikalNazivCenaRequest.getNaziv(),true);
        }else//Upit je sa And
            return porudzbinaESService.findByOcenaTekst(artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo(),artikalNazivCenaRequest.getNaziv(),false);


    }



}
