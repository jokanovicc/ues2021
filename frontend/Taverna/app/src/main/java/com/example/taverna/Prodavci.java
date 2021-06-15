package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taverna.adapter.ArtikliListaAdapter;
import com.example.taverna.adapter.KomentariAdapter;
import com.example.taverna.model.Artikal;
import com.example.taverna.model.Komentar;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.ServiceUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Prodavci extends AppCompatActivity {

    private int id;
    static final String TAG = GlavnaStranaActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private ArtikliApiService artikliApiService;
    public static SharedPreferences sharedPreferences;
    public static int idKupca;
    public static FloatingActionButton dugmeZavrsi;
    private KorisniciApiService korisniciApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodavci);
        Bundle b = getIntent().getExtras();
        if(b != null)
            id = b.getInt("ID");


        recyclerView = findViewById(R.id.rvArtikliLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView2 = findViewById(R.id.rvKomentariLista);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);

        dugmeZavrsi=findViewById(R.id.zavrsiKupovinu);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getArtikli();
        getKomentari();
        getOcena();

    }

    private void getKomentari() {

        artikliApiService = ServiceUtil.artikliApiService;
        Call<List<Komentar>> call = artikliApiService.komentariProdavca(id);
        call.enqueue(new Callback<List<Komentar>>() {


            @Override
            public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {
                recyclerView2.setAdapter(new KomentariAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Komentar>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void getOcena() {

        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<Double> call = korisniciApiService.getProsecnaOcena(id);
        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                TextView prosek = findViewById(R.id.prosekProdavac);
                prosek.setText(String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

            }
        });

    }


    private void getArtikli() {

        artikliApiService = ServiceUtil.artikliApiService;
        Call<List<Artikal>> call = artikliApiService.getArtikliProdavaca(id);
        call.enqueue(new Callback<List<Artikal>>() {


            @Override
            public void onResponse(Call<List<Artikal>> call, Response<List<Artikal>> response) {
                recyclerView.setAdapter(new ArtikliListaAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Artikal>> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }





    public void P1_bay(View view) {
    }
}