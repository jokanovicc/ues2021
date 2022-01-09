package com.example.taverna.elastic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.R;
import com.example.taverna.SearchByNazivActivity;
import com.example.taverna.adapter.ElasticSearchAdapter;
import com.example.taverna.adapter.KomentariAdapter;
import com.example.taverna.elastic.adapters.PorudzbinaSearchAdapter;
import com.example.taverna.elastic.dto.ArtikalESDTO;
import com.example.taverna.elastic.dto.PorudzbinaESDTO;
import com.example.taverna.elastic.dto.TextRequestDTO;
import com.example.taverna.servisi.ElasticSearchAPIService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchByKomentarActivity extends AppCompatActivity {

    Button mBtn;

    private RecyclerView recyclerView;
    private ElasticSearchAPIService elasticSearchAPIService;
    private EditText naziv;
    private List<PorudzbinaESDTO> porudzbinaESDTOS;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_komentar);


        mBtn = findViewById(R.id.searchKomentarButton);
        naziv = findViewById(R.id.inputKomentarSearch);


        recyclerView = findViewById(R.id.rvKomentarSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        porudzbinaESDTOS = new ArrayList<>();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiFormu();
            }
        });





    }

    private void posaljiFormu(){
        String nazivField = naziv.getText().toString();
        TextRequestDTO textRequestDTO = new TextRequestDTO();
        textRequestDTO.setText(nazivField);

        elasticSearchAPIService = RetrofitClient.elasticSearchAPIService;
        Call<List<PorudzbinaESDTO>> call = elasticSearchAPIService.searchByText(textRequestDTO);
        call.enqueue(new Callback<List<PorudzbinaESDTO>>() {


            @Override
            public void onResponse(Call<List<PorudzbinaESDTO>> call, Response<List<PorudzbinaESDTO>> response) {
                System.out.println(response.body());
                Toast.makeText(SearchByKomentarActivity.this, "Нађено резултата " + response.body().size(), Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new PorudzbinaSearchAdapter(response.body(), getApplicationContext()));
                naziv.setAlpha(0);
                mBtn.setAlpha(0);

            }

            @Override
            public void onFailure(Call<List<PorudzbinaESDTO>> call, Throwable t) {
                System.out.println("ne valja nesto");
            }
        });
    }



}
