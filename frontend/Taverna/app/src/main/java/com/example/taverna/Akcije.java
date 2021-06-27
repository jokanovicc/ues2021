package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taverna.adapter.AkcijePrikazAdapter;
import com.example.taverna.model.AkcijaPrikaz;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Akcije extends AppCompatActivity {
    private FloatingActionButton dugme;
    private RecyclerView recyclerView;
    private ArtikliApiService artikliApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akcije);


        dugme = findViewById(R.id.fab2);
        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Akcije.this, DodavanjeAkcije.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.rvAkcijePrikaz);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    protected void onResume() {
        super.onResume();
        getAkcije();
    }

    private void getAkcije(){

        artikliApiService = RetrofitClient.artikliApiService;
        Call<List<AkcijaPrikaz>> call = artikliApiService.prikazAkcija();
        call.enqueue(new Callback<List<AkcijaPrikaz>>() {
            @Override
            public void onResponse(Call<List<AkcijaPrikaz>> call, Response<List<AkcijaPrikaz>> response) {
                recyclerView.setAdapter(new AkcijePrikazAdapter(response.body(),getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<AkcijaPrikaz>> call, Throwable t) {

            }
        });



    }



}