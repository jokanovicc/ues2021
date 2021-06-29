package com.example.taverna.servisi;

import com.example.taverna.DodavanjeAkcije;
import com.example.taverna.model.AkcijaDodaj;
import com.example.taverna.model.AkcijaPrikaz;
import com.example.taverna.model.Artikal;
import com.example.taverna.model.Komentar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArtikliApiService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    @GET("artikli/prodavac/{id}")
    Call<List<Artikal>> getArtikliProdavaca(@Path("id") int id);

    @GET("artikli/{id}")
    Call<Artikal> getArtikalById(@Path("id") int id);

    @GET("artikli/prodavac-artikli")
    Call<List<Artikal>> getArtikalByProdavac();

    @POST("artikli")
    Call<Artikal> saveArtikal(@Body Artikal artikal);

    @PUT("artikli/{id}")
    Call<Artikal> updateArtikal(@Body Artikal artikal,@Path("id") int id);

    @POST("artikli/{id}")
    Call<Artikal> obrisiArtikal(@Path("id") int id);

    @GET("porudzbine/komentari/{id}")
    Call<List<Komentar>> komentariProdavca(@Path("id") int id);

    @POST("porudzbine/komentar-arhiviraj/{id}")
    Call<Komentar> arhivirajKomentar(@Path("id") int id);

    @GET("artikli/akcija_prodavca")
    Call<List<AkcijaPrikaz>> prikazAkcija();

    @POST("artikli/akcije")
    Call<Void> dodajAkciju(@Body AkcijaDodaj akcijaDodaj);

}
