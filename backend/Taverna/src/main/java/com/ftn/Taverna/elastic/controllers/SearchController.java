package com.ftn.Taverna.elastic.controllers;

import com.ftn.Taverna.elastic.controllers.dtoS.*;
import com.ftn.Taverna.elastic.services.ArtikalESService;
import com.ftn.Taverna.elastic.services.PorudzbinaESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/elastic")
public class SearchController {


    @Autowired
    private ArtikalESService artikalESService;

    @Autowired
    private PorudzbinaESService porudzbinaESService;


    @GetMapping("/naziv")
    public List<ArtikalESDto> searchByNaziv(@RequestBody ArtikalNazivRequest artikalNazivRequest){
        return artikalESService.findByNaziv(artikalNazivRequest.getNaziv());
    }

    @GetMapping("/tekst-komentara")
    public List<PorudzbinaESDto> searchByTextKomentara(@RequestBody PorudzbinaKomentarRequest porudzbinaKomentarRequest){
        return porudzbinaESService.getByTekst(porudzbinaKomentarRequest.getTekst());
    }

    @GetMapping("/price")
    public List<ArtikalESDto> searchByPrice(@RequestBody ArtikalPriceRequest artikalPriceRequest){
        return artikalESService.findByPrice(artikalPriceRequest.getFrom(),artikalPriceRequest.getTo());
    }

    @GetMapping("/rating")
    public List<PorudzbinaESDto> searchByRating(@RequestBody ArtikalPriceRequest artikalPriceRequest){
        return porudzbinaESService.findByOcena(artikalPriceRequest.getFrom(),artikalPriceRequest.getTo());
    }

}
