package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taverna.model.Artikal;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikalActivity extends AppCompatActivity {

    static final String TAG = ArtikalActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    int id;
    int id2;
    int idProdavca;
    private ArtikliApiService artikliApiService;

    private Button buttonIzmeni;
    private Button buttonObrisi;
    private TextView nazivJela;
    private TextView opisJela;
    private TextView cenaJela;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    SharedPreferences sharedPreferences;


    ImageView slika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelo_aktiviti);


        Bundle b = getIntent().getExtras();
        if(b != null)
            id = b.getInt("ID");


        nazivJela = findViewById(R.id.nazivJela);
        opisJela = findViewById(R.id.opisJela);
        cenaJela = findViewById(R.id.cenaJela);
        slika = findViewById(R.id.pojedinacnaSlika);
        buttonIzmeni = findViewById(R.id.izmeniProizvodButton);
        buttonIzmeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ArtikalActivity.this,EditArtikalActivity.class);
                Bundle b2 = new Bundle();
                b2.putInt("ID2",id2);
                intent2.putExtras(b2);
                startActivity(intent2);

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);

        String rola1 = sharedPreferences.getString(MainActivity.Role,"");


        linearLayout = findViewById(R.id.opcijeProdavac);

        if(rola1.equals("KUPAC")){
            linearLayout.setAlpha(0);
        }


    }

    @Override
    public void onResume(){
        super.onResume();
        getSelectedArtikal();
    }

    private void getSelectedArtikal(){

        artikliApiService = RetrofitClient.artikliApiService;
        Call<Artikal> call = artikliApiService.getArtikalById(id);



        call.enqueue(new Callback<Artikal>() {

            @Override
            public void onResponse(Call<Artikal> call, Response<Artikal> response) {
                nazivJela.setText(response.body().getNaziv());
                opisJela.setText(response.body().getOpis());
                cenaJela.setText(response.body().getCena().toString());
                if(response.body().getPhoto()!=null)
                    slika.setImageBitmap(response.body().getPhoto());
                id2 = response.body().getId();
                idProdavca = response.body().getProdavacId();




            }

            @Override
            public void onFailure(Call<Artikal> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });




    }


}