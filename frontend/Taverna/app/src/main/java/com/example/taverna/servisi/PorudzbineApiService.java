package com.example.taverna.servisi;

import com.example.taverna.DodavanjeRecenzije;
import com.example.taverna.Porudzbine;
import com.example.taverna.model.DodavanjeKomentara;
import com.example.taverna.model.PorucivanjeDTO;
import com.example.taverna.model.PorudzbinePrikaz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PorudzbineApiService {

    @GET("porudzbine/porudzbine-korisnika")
    Call<List<PorudzbinePrikaz>> getDospele();

    @GET("porudzbine/stiglo/{id}")
    Call<PorudzbinePrikaz> getStiglo(@Path("id") Integer id);

    @POST("porudzbine/komentar")
    Call<Void> dodavanjeRecenzije(@Body DodavanjeKomentara dodavanjeKomentara);

    @POST("porudzbine/porucivanje")
    Call<Void> napraviPorudzbinu(@Body PorucivanjeDTO porucivanjeDTO);


}
