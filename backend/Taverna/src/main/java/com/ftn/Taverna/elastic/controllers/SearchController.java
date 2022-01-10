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

    @PostMapping("/price")
    public List<ArtikalESDto> searchByPrice(@RequestBody ToFromRequestDTO toFromRequestDTO){
        return artikalESService.findByPrice(toFromRequestDTO.getFrom(), toFromRequestDTO.getTo());
    }

    @PostMapping("/rating")
    public List<PorudzbinaESDto> searchByRating(@RequestBody ToFromRequestDTO toFromRequestDTO){
        return porudzbinaESService.findByOcena(toFromRequestDTO.getFrom(), toFromRequestDTO.getTo());
    }


    @PostMapping("/naziv-cena")
    public List<ArtikalESDto> findByNazivCena(@RequestBody AndOrRequest artikalNazivCenaRequest){
        if(artikalNazivCenaRequest.isOr() == true){
            //Upit je sa Or
            return artikalESService.searchByNazivPriceOr(artikalNazivCenaRequest.getNaziv(),artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo());
        }else//Upit je sa And
            return artikalESService.searchByNazivPriceAnd(artikalNazivCenaRequest.getNaziv(),artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo());


    }

    @PostMapping("/komentar-ocena")
    public List<PorudzbinaESDto> findByTekstRating(@RequestBody AndOrRequest artikalNazivCenaRequest){
        if(artikalNazivCenaRequest.isOr() == true){
            //Upit je sa Or
            return porudzbinaESService.searchByOcenaTekstOr(artikalNazivCenaRequest.getNaziv(),artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo());
        }else//Upit je sa And
            return porudzbinaESService.searchByOcenaTekstAnd(artikalNazivCenaRequest.getNaziv(),artikalNazivCenaRequest.getFrom(),artikalNazivCenaRequest.getTo());


    }



}
