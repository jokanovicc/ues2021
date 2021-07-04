package com.ftn.Taverna.servisi;

import com.ftn.Taverna.repository.KorisnikRepository;
import com.ftn.Taverna.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikServis {

    @Autowired
    private KorisnikRepository korisnikRepository;



    public Korisnik save(Korisnik novKorisnik){
        return korisnikRepository.save(novKorisnik);
    }

    public Korisnik findOne(Integer id){
        return korisnikRepository.findById(id).orElse(null);
    }

    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public Korisnik findByUsername(String username) {
        Optional<Korisnik> user = korisnikRepository.findFirstByKorisnicko(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }


    public List<String> getKorisnicka(){
        return korisnikRepository.findAllKorisnicko();
    }

    public List<String> getMejlovi(){
        return korisnikRepository.findAllImejl();
    }



}
