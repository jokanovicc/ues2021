package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.taverna.adapter.KomentariUpravljanjeAdapter;
import com.example.taverna.model.Komentar;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KomentariActivity extends AppCompatActivity {

    private int id;
    static final String TAG = KomentariActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArtikliApiService artikliApiService;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentari);
        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);


        recyclerView = findViewById(R.id.rvKomentariUprLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }


    @Override
    protected void onResume() {
        super.onResume();
        getKomentari();

    }

    private void getKomentari() {

        artikliApiService = RetrofitClient.artikliApiService;
        Call<List<Komentar>> call = artikliApiService.komentariProdavca(sharedPreferences.getInt(String.valueOf(MainActivity.ID),1));
        call.enqueue(new Callback<List<Komentar>>() {

            @Override
            public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {
                recyclerView.setAdapter(new KomentariUpravljanjeAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Komentar>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}