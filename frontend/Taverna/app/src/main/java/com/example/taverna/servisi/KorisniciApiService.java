package com.example.taverna.servisi;

import com.example.taverna.model.IzmenaSifreDTO;
import com.example.taverna.model.Korisnik;
import com.example.taverna.model.Kupac;
import com.example.taverna.model.Login;
import com.example.taverna.model.Prodavac;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface KorisniciApiService {


    @GET("korisnici/lista-prodavaca")
    Call<List<Prodavac>> getProdavci();

    @POST("korisnici/lista-kupaca")
    Call<Boolean> registerKupca(@Body Kupac kupac);

    @POST("korisnici/lista-prodavaca")
    Call<Boolean> registerProdavac(@Body Prodavac prodavac);

    @POST("users/login")
    Call<Korisnik> login(@Body Login login);

    @GET("korisnici/moje-info")
    Call<Korisnik> getMyInfo();

    @PUT("/korisnici/izmena-info")
    Call<Korisnik> updateInfo(@Body Korisnik korisnik);

    @GET("/korisnici/svi-korisnici")
    Call<List<Korisnik>> getKorisnici();

    @POST("/korisnici/blokiraj/{id}")
    Call<Korisnik> blokirajKorisnika(@Path(("id")) int id);


    @GET("/porudzbine/prosecna-ocena/{id}")
    Call<Double> getProsecnaOcena(@Path("id") int id);

    @POST("/users/izmena-sifre")
    Call<Boolean> izmeniSifru(@Body IzmenaSifreDTO izmenaSifreDTO);

    @GET("/korisnici/prodavac-info")
    Call<Prodavac> getInfoProdavca();

    @GET("korisnici/korisnik-informacije/{id}")
    Call<Korisnik> getInfoKorisnika(@Path("id") int id);

}
