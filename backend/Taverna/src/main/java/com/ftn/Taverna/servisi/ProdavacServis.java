package com.ftn.Taverna.servisi;

import com.ftn.Taverna.repository.ProdavacRepository;
import com.ftn.Taverna.model.Prodavac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdavacServis {

    @Autowired
    private ProdavacRepository prodavacRepository;


    public List<Prodavac> findAll(){
        return prodavacRepository.findAll();
    }

    public Prodavac findOne(Integer id){
        return prodavacRepository.findById(id).orElse(null);
    }

    public Prodavac saveProdavac(Prodavac prodavac){
        return prodavacRepository.save(prodavac);

    }

    public Prodavac findByUsername(String username){
        return prodavacRepository.findByKorisnik_Korisnicko(username);
    }

    public void deleteProdavac(Prodavac prodavac){
        prodavacRepository.delete(prodavac);
    }
}
