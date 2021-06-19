package com.example.taverna;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taverna.model.Artikal;
import com.example.taverna.model.Korisnik;
import com.example.taverna.model.Prodavac;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.ServiceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilKorisnika extends AppCompatActivity {

    static final String TAG = ProfilKorisnika.class.getSimpleName();

    private KorisniciApiService korisniciApiService;
    private TextView imeProfil;
    private TextView prezimeProfil;
    private TextView adresaProfil;
    private TextView korisnickoProfil;
    private TextView rolaProfil;

    private SharedPreferences sharedPreferences;

    private Button izmeniButton;
    private Button sifraButton;
    LinearLayout prodavacLayout;
    private Prodavac prodavac;
    private TextView prodavacInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_korisnika);


        imeProfil = findViewById(R.id.imeProfil);
        adresaProfil = findViewById(R.id.adresaProfil);
        korisnickoProfil = findViewById(R.id.korisnickoProfil);
        rolaProfil = findViewById(R.id.rolaProfil);
        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);
        prodavacLayout = findViewById(R.id.prodavacPogled);
        prodavacInfo = findViewById(R.id.prodavacInfo);


        if(sharedPreferences.getString(MainActivity.Role,"").equals("KUPAC") || sharedPreferences.getString(MainActivity.Role,"").equals("ADMIN")){
            prodavacInfo.setAlpha(0);
        }


        izmeniButton = findViewById(R.id.izmeniButtonProfila);
        izmeniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilKorisnika.this,EditProfilActivity.class);
                startActivity(intent);
            }
        });

        sifraButton = findViewById(R.id.izmeniButtonSifru);
        sifraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProfilKorisnika.this,IzmenaSifreActivity.class);
                startActivity(intent1);
            }
        });

    }



    @Override
    public void onResume(){
        super.onResume();
        getSelectedKorisnik();
        if(sharedPreferences.getString(MainActivity.Role,"").equals("PRODAVAC")){
            ocitajZaProdavca();
        }
    }

    public void ocitajZaProdavca(){
        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<Prodavac> call = korisniciApiService.getInfoProdavca();
        call.enqueue(new Callback<Prodavac>() {
            @Override
            public void onResponse(Call<Prodavac> call, Response<Prodavac> response) {
                prodavac = response.body();
                String ispis = prodavac.getNaziv() + "|"+prodavac.getPoslujeOd() + "|" + prodavac.getImejl();
                prodavacInfo.setText(ispis);
            }

            @Override
            public void onFailure(Call<Prodavac> call, Throwable t) {

            }
        });



    }

    


    private void getSelectedKorisnik(){


        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<Korisnik> call = korisniciApiService.getMyInfo();


        call.enqueue(new Callback<Korisnik>() {

            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                String imePrezime = response.body().getIme() + " " + response.body().getPrezime();
                imeProfil.setText(imePrezime);
                adresaProfil.setText(response.body().getAdresa());
                korisnickoProfil.setText(response.body().getKorisnicko());
                rolaProfil.setText(response.body().getRole());
                if(sharedPreferences.getString(MainActivity.Role,"").equals("PRODAVAC")){

                }



            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });




    }



}