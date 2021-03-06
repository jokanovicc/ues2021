package com.example.taverna;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.adapter.ElasticSearchAdapter;
import com.example.taverna.elastic.SearchMenuActivity;
import com.example.taverna.elastic.dto.ArtikalESDTO;
import com.example.taverna.elastic.dto.TextRequestDTO;
import com.example.taverna.servisi.ElasticSearchAPIService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchByNazivActivity extends AppCompatActivity {

    Button mBtn;

    private RecyclerView recyclerView;
    private ElasticSearchAPIService elasticSearchAPIService;
    private EditText naziv;
    private List<ArtikalESDTO> artikalESDTOS;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_naziv);


        mBtn = findViewById(R.id.searchNazivButton);
        naziv = findViewById(R.id.inputNazivSearch);


        recyclerView = findViewById(R.id.rvNazivSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        artikalESDTOS = new ArrayList<>();

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
        Call<List<ArtikalESDTO>> call = elasticSearchAPIService.searchByNaziv(textRequestDTO);
        call.enqueue(new Callback<List<ArtikalESDTO>>() {


            @Override
            public void onResponse(Call<List<ArtikalESDTO>> call, Response<List<ArtikalESDTO>> response) {
                System.out.println(response.body());

                if(response.body().size()!=0){
                    Toast.makeText(SearchByNazivActivity.this, "???????????? ?????????????????? " + response.body().size(), Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new ElasticSearchAdapter(response.body(), getApplicationContext()));
                    naziv.setAlpha(0);
                    mBtn.setAlpha(0);
                }else{
                    Toast.makeText(SearchByNazivActivity.this, "???????????? ?????????????????? " + response.body().size(), Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchByNazivActivity.this);
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SearchByNazivActivity.this, SearchMenuActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setMessage("???????? ??????????????????");
                    builder.show();

                }


            }

            @Override
            public void onFailure(Call<List<ArtikalESDTO>> call, Throwable t) {
                System.out.println("ne valja nesto");
            }
        });
    }


}
