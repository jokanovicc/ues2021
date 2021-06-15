package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Akcije extends AppCompatActivity {
    private FloatingActionButton dugme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akcije);


        dugme = findViewById(R.id.fab2);
        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Akcije.this, DodavanjeAkcije.class);
                startActivity(intent);
            }
        });


    }
}