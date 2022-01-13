package com.example.taverna.elastic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.taverna.MainActivity;
import com.example.taverna.R;
import com.example.taverna.SearchByNazivActivity;
import com.google.android.material.card.MaterialCardView;


public class SearchMenuActivity extends AppCompatActivity {

    MaterialCardView linearLayoutNaziv;
    MaterialCardView linearLayoutKomentarTekst;
    MaterialCardView linearLayoutPriceArtikal;
    MaterialCardView linearLayoutRating;
    MaterialCardView linearLayourNazivCena;
    MaterialCardView linearLayourArtikalRating;
    MaterialCardView linearLayourArtikalKomentara;
    MaterialCardView linearLayourUkupnaCena;

    MaterialCardView linearLayourTextRating;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);

        linearLayoutNaziv=findViewById(R.id.linearNaziv);
        linearLayoutKomentarTekst=findViewById(R.id.linearKomentar);
        linearLayoutPriceArtikal = findViewById(R.id.linearArtikalPrice);
        linearLayoutRating = findViewById(R.id.linearPorudzbinaRating);
        linearLayourNazivCena = findViewById(R.id.linearArtikalNazivPrice);
        linearLayourTextRating = findViewById(R.id.linearPorudzbinaRatingText);
        linearLayourArtikalRating = findViewById(R.id.linearArtikalRating);
        linearLayourArtikalKomentara = findViewById(R.id.linearArtikalKomentara);
        linearLayourUkupnaCena = findViewById(R.id.linearCenaPorudzbine);

        linearLayoutNaziv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByNazivActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutKomentarTekst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByKomentarActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutPriceArtikal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByPriceArtikal.class);
                startActivity(intent);
            }
        });
        linearLayoutRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByRatingPorudzbina.class);
                startActivity(intent);
            }
        });

        linearLayourNazivCena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByNazivPrice.class);
                startActivity(intent);
            }
        });

        linearLayourTextRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByRatingText.class);
                startActivity(intent);
            }
        });

        linearLayourArtikalRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchArtikalByOcena.class);
                startActivity(intent);
            }
        });

        linearLayourArtikalKomentara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByBrojKomentaraArtikla.class);
                startActivity(intent);
            }
        });

        linearLayourUkupnaCena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMenuActivity.this, SearchByPorudzbinaCena.class);
                startActivity(intent);
            }
        });

    }





}
