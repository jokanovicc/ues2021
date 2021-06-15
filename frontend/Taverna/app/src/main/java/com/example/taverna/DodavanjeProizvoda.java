package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taverna.model.Artikal;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.ServiceUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DodavanjeProizvoda extends AppCompatActivity{

    private Button dugme;
    private ImageView slika;
    private static final int RESULT_LOAD_IMG =1;


    private RecyclerView recyclerView;
    SharedPreferences sharedPreferences;

    private ArtikliApiService artikliApiService;
    private EditText naziv;
    private EditText opis;
    private EditText cena;
    String nesto;
    private Button button;
    private Button buttonDodaj;

    private Button btnPhoto;
    private Bitmap bitmap;
    private JsonWriter jsonWriter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_proizvoda);


        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);

        nesto = "ovo je "+sharedPreferences.getInt(String.valueOf(MainActivity.ID),1);


        btnPhoto = findViewById(R.id.photoCreate);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });



        naziv = findViewById(R.id.nazivProizvoda);
        opis = findViewById(R.id.opisProizvoda);
        cena = findViewById(R.id.cenaProizvoda);

        buttonDodaj = findViewById(R.id.dugmeDodajProizvod);
        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajProizvod();

            }
        });





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                ImageView image = findViewById(R.id.imageContainer);
                image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void dodajProizvod() {
        final Artikal artikal = new Artikal();
        String nazivJela = naziv.getText().toString();
        String cena1 = cena.getText().toString();
        String opisJela = opis.getText().toString();

        if (cena1.length() == 0) {
            cena.requestFocus();
            cena.setError("Морате унети цену");
        } else {

            int cenaInt = Integer.parseInt(cena1);
            if(cenaInt<1){
                cena.requestFocus();
                cena.setError("Не може цена бити негативан број");
            }

            if (nazivJela.length() == 0) {
                naziv.requestFocus();
                naziv.setError("НАЗИВ ЈЕЛА НЕ СМЕ БИТИ ПРАЗАН");
            } else if (opisJela.length() == 0) {
                opis.requestFocus();
                opis.setError("ОПИС НЕ СМЕ БИТИ ПРАЗАН");

            } else {


                artikal.setPhoto(bitmap);
                artikal.setCena(cenaInt);
                artikal.setNaziv(nazivJela);
                artikal.setOpis(opisJela);
                artikal.setProdavacId(sharedPreferences.getInt(String.valueOf(MainActivity.ID), 1)); // za sad ovako


                artikliApiService = ServiceUtil.artikliApiService;
                Call<Artikal> call = artikliApiService.saveArtikal(artikal);
                call.enqueue(new Callback<Artikal>() {
                    @Override
                    public void onResponse(Call<Artikal> call, Response<Artikal> response) {
                        Toast.makeText(DodavanjeProizvoda.this, "Успешно додат производ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DodavanjeProizvoda.this, GlavnaStranaActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Artikal> call, Throwable t) {
                        Toast.makeText(DodavanjeProizvoda.this, "Нешто не ваља", Toast.LENGTH_SHORT).show();

                    }
                });


            }


        }
    }
















    }