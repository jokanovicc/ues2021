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

import com.example.taverna.adapter.ArtikliListaAdapter;
import com.example.taverna.adapter.ArtikliProdavcaAdapter;
import com.example.taverna.model.Artikal;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.ServiceUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProdavacGlavnaActivity extends AppCompatActivity {


    static final String TAG = GlavnaStranaActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    SharedPreferences sharedPreferences;

    private ArtikliApiService artikliApiService;
    private FloatingActionButton dugmeDodaj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodavac_glavna);
        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);


        recyclerView = findViewById(R.id.rvArtikliListaProdavac);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dugmeDodaj = findViewById(R.id.dugmeDodaj);
        dugmeDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ProdavacGlavnaActivity.this, DodavanjeProizvoda.class);
                startActivity(intent2);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        getArtikli();

    }



    private void getArtikli() {

        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);

        artikliApiService = ServiceUtil.artikliApiService;

        Call<List<Artikal>> call = artikliApiService.getArtikliProdavaca(sharedPreferences.getInt(String.valueOf(MainActivity.ID),1));
        call.enqueue(new Callback<List<Artikal>>() {


            @Override
            public void onResponse(Call<List<Artikal>> call, Response<List<Artikal>> response) {
                recyclerView.setAdapter(new ArtikliProdavcaAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Artikal>> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }





}