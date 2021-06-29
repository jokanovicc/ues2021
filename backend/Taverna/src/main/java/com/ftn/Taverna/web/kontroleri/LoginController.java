package com.ftn.Taverna.web.kontroleri;
import com.ftn.Taverna.web.kontroleri.DTO.IzmenaSifreDTO;
import com.ftn.Taverna.web.kontroleri.DTO.KorisnikDTO;
import com.ftn.Taverna.web.kontroleri.DTO.Login;
import com.ftn.Taverna.model.Korisnik;
import com.ftn.Taverna.security.TokenUtils;
import com.ftn.Taverna.servisi.KorisnikServis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    KorisnikServis korisnikServis;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TokenUtils tokenUtils;


    private static final Logger LOG = LogManager.getLogger(LoginController.class);


    @PostMapping("/login")
    public ResponseEntity<KorisnikDTO> login(@RequestBody Login userDto) {

        Korisnik korisnik = korisnikServis.findByUsername(userDto.getKorisnicko());
        if (korisnik!=null && korisnik.isBlokiran()) {
            LOG.error("Korisnik je blokiran");
            return ResponseEntity.notFound().build();

        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getKorisnicko(), userDto.getSifra());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("sam ovde_ ");
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getKorisnicko());
            Korisnik korisnik1 = korisnikServis.findByUsername(userDto.getKorisnicko());
            KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik1);
            korisnikDTO.setToken(tokenUtils.generateToken(userDetails));
            LOG.info("Na klijensku stranu poslat token " + korisnikDTO.getToken());
            return ResponseEntity.ok(korisnikDTO);
        } catch (UsernameNotFoundException e) {
            LOG.error("Nije pronađen takav korisnik");
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("izmena-sifre")
    private ResponseEntity<Boolean> izmeniSifru(@RequestBody IzmenaSifreDTO izmenaSifreDTO, Authentication authentication) {
        System.out.println("STIGAO");
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        System.out.println("STIGAO" + userPrincipal.getUsername());
        String korisnicko = userPrincipal.getUsername();
        Korisnik korisnik = korisnikServis.findByUsername(korisnicko);
        boolean podudaravanje = BCrypt.checkpw(izmenaSifreDTO.getStaraSifra(), korisnik.getSifra());
        if (!podudaravanje) {
            return new ResponseEntity<>(false, HttpStatus.OK);


        }
        korisnik.setSifra(passwordEncoder.encode(izmenaSifreDTO.getNovaSifra()));
        korisnikServis.save(korisnik);
        return new ResponseEntity<>(true, HttpStatus.OK);


    }


}
