package com.example.taverna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.model.Kupac;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    static final String TAG = RegisterActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    private KorisniciApiService korisniciApiService;
    private EditText ime;
    private EditText prezime;
    private EditText sifra;
    private EditText adresa;
    private EditText korisnicko;

    private List<String> imena = new ArrayList<>();
    private Button button;

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        ime = findViewById(R.id.imeRegistracija);
        prezime = findViewById(R.id.prezimeRegistracija);
        sifra = findViewById(R.id.sifraRegistracija);
        adresa = findViewById(R.id.adresaRegistracija);
        korisnicko = findViewById(R.id.korisnickoRegistracija);


        button = findViewById(R.id.buttonRegistracija);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajKorisnika();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        validateImejl();
    }

    public void dodajKorisnika() {


        korisniciApiService = RetrofitClient.korisniciApiService;
        final Kupac kupac = new Kupac();
        String imen = ime.getText().toString();
        String prezimen = prezime.getText().toString();
        String pass = sifra.getText().toString();
        String adress = adresa.getText().toString();
        String korisnickoIme = korisnicko.getText().toString();




        if (imena.contains(korisnickoIme)) {
            korisnicko.requestFocus();
            korisnicko.setError("Већ заузето корисничко име");
        } else {

            if (korisnickoIme.length() == 0) {
                korisnicko.requestFocus();
                korisnicko.setError("ИМЕ НЕ СМЕ БИТИ ПРЕЗНО");
            }
            else if (prezimen.length() == 0) {
                prezime.requestFocus();
                prezime.setError("ПРЕЗИМЕ НЕ СМЕ БИТИ ПРЕЗНО");
            } else if (pass.length() == 0) {
                sifra.requestFocus();
                sifra.setError("ЛОЗИНКА НЕ СМЕ БИТИ ПРЕЗНА");
            } else if (adress.length() == 0) {
                adresa.requestFocus();
                adresa.setError("АДРЕСА НЕ СМЕ БИТИ ПРАЗНА");
            } else if (imen.length() == 0) {
                ime.requestFocus();
                ime.setError("КОРИСНИЧКО ИМЕ НЕ СМЕ БИТИ ПРЕЗНО");

            } else {
                Toast.makeText(RegisterActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
                kupac.setAdresa(adress);
                kupac.setPrezime(prezimen);
                kupac.setIme(imen);
                kupac.setSifra(pass);
                kupac.setKorisnicko(korisnickoIme);

                Call<Kupac> call = korisniciApiService.registerKupca(kupac);
                call.enqueue(new Callback<Kupac>() {
                    @Override
                    public void onResponse(Call<Kupac> call, Response<Kupac> response) {
                        Toast.makeText(RegisterActivity.this, "Успешно регистрован", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Kupac> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "ПУКЛА ВЕЗА", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }


    public void validateImejl() {
        korisniciApiService = RetrofitClient.korisniciApiService;
        Call<List<String>> call = korisniciApiService.getKorisnicka();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                imena = response.body();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

}