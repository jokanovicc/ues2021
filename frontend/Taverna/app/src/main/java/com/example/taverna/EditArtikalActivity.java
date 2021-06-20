package com.example.taverna;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taverna.model.Artikal;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditArtikalActivity extends AppCompatActivity {

    static final String TAG = ArtikalActivity.class.getSimpleName();

    private ArtikliApiService artikliApiService;

    private Artikal artikal = new Artikal();

    private Button button;
    int id;
    private EditText editNaziv;
    private EditText editOpis;
    private EditText editCena;


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artikal);


        Bundle b = getIntent().getExtras();
        if(b != null)
            id = b.getInt("ID2");


        editNaziv = findViewById(R.id.nazivEdit);
        editOpis = findViewById(R.id.opisEdit);
        editCena = findViewById(R.id.cenaEdit);



        artikliApiService = RetrofitClient.artikliApiService;
        Call<Artikal> call = artikliApiService.getArtikalById(id);
        call.enqueue(new Callback<Artikal>() {
            @Override
            public void onResponse(Call<Artikal> call, Response<Artikal> response) {
                artikal = response.body();

                EditText naziv = findViewById(R.id.nazivEdit);
                naziv.setText(artikal.getNaziv());

                EditText opis = findViewById(R.id.opisEdit);
                opis.setText(artikal.getOpis());

                EditText cena = findViewById(R.id.cenaEdit);
                cena.setText(artikal.getCena().toString());


            }

            @Override
            public void onFailure(Call<Artikal> call, Throwable t) {

            }
        });


        button = findViewById(R.id.editProizvoda);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArtikal();
            }
        });



    }

    public void updateArtikal(){

        artikliApiService = RetrofitClient.artikliApiService;
        Call<Artikal> call = artikliApiService.updateArtikal(artikal,artikal.getId());


        call.enqueue(new Callback<Artikal>() {
            @Override
            public void onResponse(Call<Artikal> call, Response<Artikal> response) {
                String nazivPost = editNaziv.getText().toString();
                String opisPost = editOpis.getText().toString();
                String cenaPost = editCena.getText().toString();


                if(nazivPost.length()==0){
                    editNaziv.requestFocus();
                    editNaziv.setError("НАЗИВ ЈЕЛА НЕ СМЕ БИТИ ПРАЗНО");
                }
                if(opisPost.length()==0){
                    editOpis.requestFocus();
                    editOpis.setError("ОПИС НЕ СМЕ БИТИ ПРАЗАН");
                }

                else {

                    artikal.setNaziv(nazivPost);
                    artikal.setOpis(opisPost);
                    artikal.setCena(Integer.parseInt(cenaPost));

                }
            }

            @Override
            public void onFailure(Call<Artikal> call, Throwable t) {

            }
        });


    }





}