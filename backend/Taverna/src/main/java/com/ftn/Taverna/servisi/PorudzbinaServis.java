package com.ftn.Taverna.servisi;


import com.ftn.Taverna.repository.PorudzbinaRepository;
import com.ftn.Taverna.model.Porudzbina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaServis {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public List<Porudzbina> findAll(){
        return porudzbinaRepository.findAll();
    }

    public Porudzbina findOne(Integer id){
        return porudzbinaRepository.findById(id).orElse(null);
    }

    public Porudzbina save(Porudzbina porudzbina){
        return porudzbinaRepository.save(porudzbina);
    }

    public List<Porudzbina> findByKupacId(Integer id){
        return porudzbinaRepository.findPorudzbinaByKupac_Id(id);
    }

    public List<Porudzbina> getKomentari(Integer id){
        return porudzbinaRepository.getKomentari(id);
    }

    public List<String> getNaziviArtikala(Integer id){
        return porudzbinaRepository.getNaziviArtikala(id);
    }

    public Double getProsecnaOcenaProdavca(Integer id){
        return porudzbinaRepository.getProsecnaOcenaProdavca(id);
    }


}
