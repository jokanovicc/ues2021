package com.example.taverna;

import android.app.AlertDialog;
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
    }

    public void dodajKorisnika() {


        korisniciApiService = RetrofitClient.korisniciApiService;
        final Kupac kupac = new Kupac();
        String imen = ime.getText().toString();
        String prezimen = prezime.getText().toString();
        String pass = sifra.getText().toString();
        String adress = adresa.getText().toString();
        String korisnickoIme = korisnicko.getText().toString();

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

                Call<Boolean> call = korisniciApiService.registerKupca(kupac);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body()) {
                            Toast.makeText(RegisterActivity.this, "Успешно регистрован", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(!response.body()){
                            AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                            alert.setTitle("Грешка");
                            alert.setMessage("Корисничко име " + korisnickoIme + " је заузето. Молим изаберите неко друго!");
                            alert.setPositiveButton("OK",null);
                            alert.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "ПУКЛА ВЕЗА", Toast.LENGTH_SHORT).show();

                    }
                });
            }

    }



}