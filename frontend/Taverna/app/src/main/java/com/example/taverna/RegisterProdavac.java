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

import com.example.taverna.model.Prodavac;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterProdavac extends AppCompatActivity {

    static final String TAG = RegisterActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    private EditText ime;
    private EditText prezime;
    private EditText sifra;
    private EditText adresa;
    private EditText korisnicko;
    private EditText imejl;
    private EditText naziv;
    private KorisniciApiService korisniciApiService;
    private List<String> imena = new ArrayList<>();
    private List<String> imejlovi = new ArrayList<>();

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_prodavac);


        ime = findViewById(R.id.imeRegistracija2);
        prezime = findViewById(R.id.prezimeRegistracija2);
        sifra = findViewById(R.id.sifraRegistracija2);
        adresa = findViewById(R.id.adresaRegistracija2);
        korisnicko = findViewById(R.id.korisnickoRegistracija2);
        naziv = findViewById(R.id.nazivRegistracija);
        imejl = findViewById(R.id.imejlRegistracija);



        button = findViewById(R.id.buttonRegistracija2);
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
        final Prodavac prodavac = new Prodavac();
        String imen = ime.getText().toString();
        String prezimen = prezime.getText().toString();
        String pass = sifra.getText().toString();
        String adress = adresa.getText().toString();
        String korisnickoIme = korisnicko.getText().toString();
        String email = imejl.getText().toString();
        String preduzece = naziv.getText().toString();

                if (korisnickoIme.length() == 0) {
                    korisnicko.requestFocus();
                    korisnicko.setError("?????? ???? ?????? ???????? ????????????");
                } else if (prezimen.length() == 0) {
                    prezime.requestFocus();
                    prezime.setError("?????????????? ???? ?????? ???????? ????????????");
                } else if (pass.length() == 0) {
                    sifra.requestFocus();
                    sifra.setError("?????????????? ???? ?????? ???????? ????????????");
                } else if (adress.length() == 0) {
                    adresa.requestFocus();
                    adresa.setError("???????????? ???? ?????? ???????? ????????????");
                } else if (imen.length() == 0) {
                    ime.requestFocus();
                    ime.setError("???????????????????? ?????? ???? ?????? ???????? ????????????");

                } else if (preduzece.length() == 0) {
                    naziv.requestFocus();
                    naziv.setError("?????????? ?????????????????? ???? ?????? ???????? ????????????");
                } else if (email.length() == 0) {
                    imejl.requestFocus();
                    imejl.setError("???????????????? ?????? ???????????? ??????????");
                }else if(email.contains("@")==false){
                    imejl.requestFocus();
                    imejl.setError("????????????????  ??????????");
                } else {
                    prodavac.setAdresa(adress);
                    prodavac.setPrezime(prezimen);
                    prodavac.setIme(imen);
                    prodavac.setSifra(pass);
                    prodavac.setKorisnicko(korisnickoIme);
                    prodavac.setImejl(email);
                    prodavac.setNaziv(preduzece);


                    Call<Boolean> call = korisniciApiService.registerProdavac(prodavac);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (!response.body()) {
                                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterProdavac.this);
                                alert.setTitle("??????????????????????");
                                alert.setMessage("???????????????????? ?????? ?????? ?????????? ???? ?????? ??????????????. ?????????? ?????? ?????????????????? ???????? ??????????!");
                                alert.setPositiveButton("OK", null);
                                alert.show();
                            } else {
                                Toast.makeText(RegisterProdavac.this, "?????????????? ??????????????????????", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterProdavac.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(RegisterProdavac.this, "?????????? ????????", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        }

