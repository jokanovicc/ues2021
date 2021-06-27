package com.ftn.Taverna.servisi;

import com.ftn.Taverna.repository.ArtikalRepository;
import com.ftn.Taverna.model.Artikal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ArtikliServis {

    @Autowired
    private ArtikalRepository artikalRepository;

    public List<Artikal> findAll(){
        return artikalRepository.findAll();
    }

    public Artikal findOne(Integer id){
        return artikalRepository.findById(id).orElse(null);
    }

    public Artikal saveArtikal(Artikal artikal){
        return artikalRepository.save(artikal);
    }

    public void deleteArtikal(Artikal artikal){
        artikalRepository.delete(artikal);
    }

    public List<Artikal> findByProdavac(Integer id){
        return artikalRepository.findByProdavac_Id(id);
    }

    public Double procenatPopusta(Integer id, Date datum){
        if(artikalRepository.getProcenatAkcije(id,datum)!=null){
            return artikalRepository.getProcenatAkcije(id,datum);

        }else{
            return 0.0;
        }
    }
}
