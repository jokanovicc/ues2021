package com.example.taverna;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taverna.model.Artikal;
import com.example.taverna.model.Korisnik;
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
    static final String BASE_URL = "http://192.168.1.4:8080/";
    static Retrofit retrofit = null;

    private KorisniciApiService korisniciApiService;
    private TextView imeProfil;
    private TextView prezimeProfil;
    private TextView adresaProfil;
    private TextView korisnickoProfil;
    private TextView rolaProfil;

    private SharedPreferences sharedPreferences;

    private Button izmeniButton;
    private Button sifraButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_korisnika);


        imeProfil = findViewById(R.id.imeProfil);
        adresaProfil = findViewById(R.id.adresaProfil);
        korisnickoProfil = findViewById(R.id.korisnickoProfil);
        rolaProfil = findViewById(R.id.rolaProfil);
        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);


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


            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });




    }



}