package com.ftn.Taverna.servisi;

import com.ftn.Taverna.repository.KupacRepository;
import com.ftn.Taverna.model.Kupac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KupacServis {

    @Autowired
    private KupacRepository kupacRepository;

    public List<Kupac> findAll(){
        return kupacRepository.findAll();
    }

    public Kupac findOne(Integer id){
        return kupacRepository.findById(id).orElse(null);
    }

    public Kupac saveKupac(Kupac kupac){
        return kupacRepository.save(kupac);
    }

    public void deleteKupac(Kupac kupac){
        kupacRepository.delete(kupac);
    }
}
