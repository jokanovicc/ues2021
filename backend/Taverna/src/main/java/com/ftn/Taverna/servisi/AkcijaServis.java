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
}
