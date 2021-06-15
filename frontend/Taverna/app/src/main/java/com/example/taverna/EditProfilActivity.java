package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taverna.model.Korisnik;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.ServiceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {


    static final String TAG = EditProfilActivity.class.getSimpleName();

    private KorisniciApiService korisniciApiService;

    private Korisnik korisnik = new Korisnik();


    private Button button;

    private EditText editIme;
    private EditText editPrezime;
    private EditText editSifra;
    private EditText editAdresa;


    private SharedPreferences sharedPreferences;




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);


        editIme = findViewById(R.id.imeEdit);
        editPrezime = findViewById(R.id.prezimeEdit);
        editSifra = findViewById(R.id.sifraEdit);
        editAdresa = findViewById(R.id.adresaEdit);


        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<Korisnik> call = korisniciApiService.getMyInfo();

        call.enqueue(new Callback<Korisnik>() {
            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                korisnik = response.body();

                EditText ime = findViewById(R.id.imeEdit);
                ime.setText(korisnik.getIme());

                EditText prezime = findViewById(R.id.prezimeEdit);
                prezime.setText(korisnik.getPrezime());

                EditText sifra = findViewById(R.id.sifraEdit);
                sifra.setText(korisnik.getSifra());

                EditText adresa = findViewById(R.id.adresaEdit);
                adresa.setText(korisnik.getAdresa());


            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {

            }
        });


        button = findViewById(R.id.buttonProfilEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKorisnik();
            }
        });





    }


    public void updateKorisnik(){

        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<Korisnik> call = korisniciApiService.updateInfo(korisnik);


        call.enqueue(new Callback<Korisnik>() {
            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                String imePost = editIme.getText().toString();
                String prezimePost = editPrezime.getText().toString();
                String adresaPost = editAdresa.getText().toString();
                String sifraPost = editSifra.getText().toString();

                if(imePost.length()==0){
                    editIme.requestFocus();
                    editIme.setError("ИМЕ НЕ СМЕ БИТИ ПРАЗНО");
                }
                if(prezimePost.length()==0){
                    editPrezime.requestFocus();
                    editPrezime.setError("ПРЕЗИМЕ НЕ СМЕ БИТИ ПРАЗНО");
                }
                if(adresaPost.length()==0){
                    editAdresa.requestFocus();
                    editAdresa.setError("АДРЕСА НЕ СМЕ БИТИ ПРАЗНО");
                }
                if(sifraPost.length()==0){
                    editSifra.requestFocus();
                    editSifra.setError("ШИФРА НЕ СМЕ БИТИ ПРАЗНО");
                }

                else {


                    korisnik.setIme(imePost);
                    korisnik.setPrezime(prezimePost);
                    korisnik.setAdresa(adresaPost);
                    korisnik.setSifra(sifraPost);

                }
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {

            }
        });


    }
}