package com.ftn.Taverna.servisi;

import com.ftn.Taverna.repository.AkcijaRepository;
import com.ftn.Taverna.model.Akcija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AkcijaServis {

    @Autowired
    private AkcijaRepository akcijaRepository;

    public List<Akcija> findAll(){
        return akcijaRepository.findAll();
    }

    public Akcija saveAkcija(Akcija akcija){
        return akcijaRepository.save(akcija);
    }

    public List<Akcija> akcijeProdavca(Integer id){
        return akcijaRepository.getAkcijeProdavca(id);
    }

    public Akcija findById(Integer id){
        return akcijaRepository.findById(id).orElse(null);
    }

    public void deleteAkcija(Akcija akcija){
        akcijaRepository.delete(akcija);
    }

    public List<Akcija> getByProdavac(Integer id){
        return akcijaRepository.getAkcijaByProdavac_Korisnik_Id(id);
    }


    public List<String> artikliNaAkcijiPrikaz(Integer id){
        return akcijaRepository.artikliAkcijaPrikaz(id);
    }
}
