package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.taverna.adapter.KorisniciAdapter;
import com.example.taverna.adapter.ProdavciListaAdapter;
import com.example.taverna.model.Korisnik;
import com.example.taverna.model.Prodavac;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.ServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Korisnici extends AppCompatActivity {

   // Switch switchDugme;
    static final String TAG = GlavnaStranaActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private KorisniciApiService korisniciApiService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korisnici);


        recyclerView = findViewById(R.id.rvKorisniciUpr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





//        switchDugme = findViewById(R.id.blokiranje);
//        switchDugme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(Korisnici.this, "Корисник Блокиран",Toast.LENGTH_SHORT).show();
//            }
//        });





    }


    @Override
    protected void onResume() {
        super.onResume();
        getKorisnici();
    }

    public void profilKlik(View view) {
        Intent i = new Intent(Korisnici.this, ProfilKorisnika.class);
        startActivity(i);
    }


    private void getKorisnici(){

        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<List<Korisnik>> call = korisniciApiService.getKorisnici();
        call.enqueue(new Callback<List<Korisnik>>() {
            @Override
            public void onResponse(Call<List<Korisnik>> call, Response<List<Korisnik>> response) {
                recyclerView.setAdapter(new KorisniciAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Korisnik>> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });


    }
}