package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.taverna.adapter.ProdavciListaAdapter;
import com.example.taverna.model.Prodavac;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaProdavaca extends AppCompatActivity {

    static final String TAG = GlavnaStranaActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private KorisniciApiService korisniciApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prodavaca);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        recyclerView = findViewById(R.id.rvProdavciLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
        getProdavci();

    }
    private void getProdavci(){


        korisniciApiService = RetrofitClient.korisniciApiService;
        Call<List<Prodavac>> call = korisniciApiService.getProdavci();
        call.enqueue(new Callback<List<Prodavac>>() {
            @Override
            public void onResponse(Call<List<Prodavac>> call, Response<List<Prodavac>> response) {
                recyclerView.setAdapter(new ProdavciListaAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Prodavac>> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });


    }




}