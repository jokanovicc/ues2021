package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taverna.model.DodavanjeKomentara;
import com.example.taverna.servisi.PorudzbineApiService;
import com.example.taverna.servisi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DodavanjeRecenzije extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int id;
    private Button dugme;

    private RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    private PorudzbineApiService porudzbineApiService;


    int ocena1 =1;
    private EditText komentar;
    private EditText ocena;
    private CheckBox anoniman;
    private Button dodaj;
    private Spinner spinner;
    private static final String[] paths = {"1","2","3","4","5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_recenzije);

        Bundle b = getIntent().getExtras();
        if(b != null)
            id = b.getInt("ID");

        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);

        komentar = findViewById(R.id.komentarRecenzija);

        spinner = (Spinner)findViewById(R.id.spinner_ocena);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DodavanjeRecenzije.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        anoniman = findViewById(R.id.checkbox_anoniman);
        dodaj = findViewById(R.id.btnDodajRecenziju);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajRecenziju();
            }
        });







    }


    public void dodajRecenziju(){

        final DodavanjeKomentara recenzija = new DodavanjeKomentara();
        String komentarKomentara = komentar.getText().toString();



        if(komentarKomentara.length()==0){
            komentar.requestFocus();
            komentar.setError("Морате унети текст рецензије");
        }else {
            recenzija.setAnoniman(anoniman.isChecked());
            recenzija.setKomentar(komentarKomentara);
            recenzija.setOcena(ocena1);
            recenzija.setKupac(sharedPreferences.getInt(String.valueOf(MainActivity.ID), 1));
            recenzija.setPorudzbina(id);


            porudzbineApiService = RetrofitClient.porudzbineApiService;
            Call<Void> call = porudzbineApiService.dodavanjeRecenzije(recenzija);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(DodavanjeRecenzije.this, "Успешно додат производ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DodavanjeRecenzije.this, GlavnaStranaActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(DodavanjeRecenzije.this, "ГРЕШКА", Toast.LENGTH_SHORT).show();


                }
            });


        }





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        ocena1 = Integer.parseInt(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}