package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.taverna.adapter.PorudzbineAdapter;
import com.example.taverna.model.PorudzbinePrikaz;
import com.example.taverna.servisi.PorudzbineApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PorudzbineActivity extends AppCompatActivity {

    private int id;
    static final String TAG = GlavnaStranaActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private PorudzbineApiService porudzbineApiService;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porudzbine);

        recyclerView = findViewById(R.id.rvPorudzbineLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);






    }


    @Override
    protected void onResume() {
        super.onResume();
        getPorudzbine();

    }


    private void getPorudzbine() {

        porudzbineApiService = RetrofitClient.porudzbineApiService;
        Call<List<PorudzbinePrikaz>> call = porudzbineApiService.getDospele();
        call.enqueue(new Callback<List<PorudzbinePrikaz>>() {


            @Override
            public void onResponse(Call<List<PorudzbinePrikaz>> call, Response<List<PorudzbinePrikaz>> response) {
                recyclerView.setAdapter(new PorudzbineAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<PorudzbinePrikaz>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}