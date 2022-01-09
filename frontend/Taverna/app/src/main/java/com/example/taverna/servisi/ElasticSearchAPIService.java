package com.example.taverna.servisi;

import com.example.taverna.elastic.dto.ArtikalESDTO;
import com.example.taverna.elastic.dto.PorudzbinaESDTO;
import com.example.taverna.elastic.dto.TextRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ElasticSearchAPIService {



    @POST("elastic/naziv")
    Call<List<ArtikalESDTO>> searchByNaziv(@Body TextRequestDTO textRequestDTO);

    @POST("elastic/tekst-komentara")
    Call<List<PorudzbinaESDTO>> searchByText(@Body TextRequestDTO textRequestDTO);



}
