package com.ftn.Taverna.elastic;

import com.ftn.Taverna.elastic.model.ArtikalES;
import com.ftn.Taverna.elastic.model.PorudzbinaES;
import com.ftn.Taverna.elastic.repository.ArtikalEsRepository;
import com.ftn.Taverna.elastic.repository.PorudzbinaEsRepository;
import com.ftn.Taverna.elastic.services.ArtikalESService;
import com.ftn.Taverna.elastic.services.PorudzbinaESService;
import com.ftn.Taverna.model.Artikal;
import com.ftn.Taverna.model.Porudzbina;
import com.ftn.Taverna.model.Stavka;
import com.ftn.Taverna.repository.ArtikalRepository;
import com.ftn.Taverna.repository.PorudzbinaRepository;
import com.ftn.Taverna.repository.StavkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loader {

    /*Komponenta služi da prilikom svakog pokretanja podatke iz sql ubacuje u elastic search bazu*/

    @Autowired
    ElasticsearchOperations elasticsearchOperations;


    @Autowired
    ArtikalRepository artikalRepository;

    @Autowired
    ArtikalEsRepository artikalEsRepository;

    @Autowired
    PorudzbinaEsRepository porudzbinaEsRepository;


    @Autowired
    PorudzbinaRepository porudzbinaRepository;


    @Autowired
    ArtikalESService artikalESService;

    @Autowired
    PorudzbinaESService porudzbinaESService;

    @PostConstruct
    @Transactional
    public void loadAll(){

        artikalEsRepository.deleteAll();
        porudzbinaEsRepository.deleteAll();
        System.out.println("Loading Data");
        List<ArtikalES> artikli = new ArrayList<>();
        for(Artikal artikal: artikalRepository.findAll()){
            artikli.add(new ArtikalES(artikal));
        }
        for(ArtikalES artikalES: artikli){
            artikalES.setRating(artikalESService.getRating(artikalES));
            artikalES.setKomentara(artikalESService.getBrojKomentara(artikalES));
        }
        artikalEsRepository.saveAll(artikli);


        List<PorudzbinaES> porudzbinaES = new ArrayList<>();
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            porudzbinaES.add(new PorudzbinaES(porudzbina));
        }
        for(PorudzbinaES p: porudzbinaES){
            p.setCena(porudzbinaESService.getUkupnaCenaPorudzbine(p));
        }

        porudzbinaEsRepository.saveAll(porudzbinaES);


        System.out.println("Loading Completed");



    }


}
