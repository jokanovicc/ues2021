package com.example.taverna.servisi;

import com.example.taverna.elastic.dto.AndOrRequest;
import com.example.taverna.elastic.dto.ArtikalESDTO;
import com.example.taverna.elastic.dto.PorudzbinaESDTO;
import com.example.taverna.elastic.dto.TextRequestDTO;
import com.example.taverna.elastic.dto.ToFromRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ElasticSearchAPIService {



    @POST("elastic/naziv")
    Call<List<ArtikalESDTO>> searchByNaziv(@Body TextRequestDTO textRequestDTO);

    @POST("elastic/price")
    Call<List<ArtikalESDTO>> searchByPrice(@Body ToFromRequestDTO toFromRequestDTO);

    @POST("elastic/tekst-komentara")
    Call<List<PorudzbinaESDTO>> searchByText(@Body TextRequestDTO textRequestDTO);

    @POST("elastic/rating")
    Call<List<PorudzbinaESDTO>> searchByRating(@Body ToFromRequestDTO toFromRequestDTO);


    @POST("elastic/naziv-cena")
    Call<List<ArtikalESDTO>> searchByNazivCena(@Body AndOrRequest andOrRequest);

    @POST("elastic/komentar-ocena")
    Call<List<PorudzbinaESDTO>> searchByRatingText(@Body AndOrRequest andOrRequest);


}
