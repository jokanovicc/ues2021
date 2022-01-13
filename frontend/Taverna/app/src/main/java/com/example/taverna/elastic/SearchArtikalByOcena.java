package com.example.taverna.elastic;




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

import com.example.taverna.R;
import com.example.taverna.adapter.ElasticSearchAdapter;
import com.example.taverna.elastic.SearchMenuActivity;
import com.example.taverna.elastic.adapters.ArtikalExtendedAdapter;
import com.example.taverna.elastic.dto.ArtikalESDTO;
import com.example.taverna.elastic.dto.TextRequestDTO;
import com.example.taverna.elastic.dto.ToFromRequestDTO;
import com.example.taverna.servisi.ElasticSearchAPIService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchArtikalByOcena extends AppCompatActivity {

    Button mBtn;
    private RecyclerView recyclerView;
    private ElasticSearchAPIService elasticSearchAPIService;
    private EditText from;
    private EditText to;
    private List<ArtikalESDTO> artikalESDTOS;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_artikal_ocena);


        mBtn = findViewById(R.id.searchRatingArtiklaButton);
        from = findViewById(R.id.inputRatingArtiklaFrom);
        to = findViewById(R.id.inputRatingArtiklaTo);


        recyclerView = findViewById(R.id.rvRatingArtiklaSearch);
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
        Integer fromPrice = Integer.parseInt(from.getText().toString());
        Integer toPrice = Integer.parseInt(to.getText().toString());

        ToFromRequestDTO toFromRequestDTO = new ToFromRequestDTO();
        toFromRequestDTO.setFrom(fromPrice);
        toFromRequestDTO.setTo(toPrice);


        elasticSearchAPIService = RetrofitClient.elasticSearchAPIService;
        Call<List<ArtikalESDTO>> call = elasticSearchAPIService.searchByRatingArtikla(toFromRequestDTO);
        call.enqueue(new Callback<List<ArtikalESDTO>>() {


            @Override
            public void onResponse(Call<List<ArtikalESDTO>> call, Response<List<ArtikalESDTO>> response) {
                System.out.println(response.body());

                if(response.body().size()!=0){
                    Toast.makeText(SearchArtikalByOcena.this, "Нађено резултата " + response.body().size(), Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new ArtikalExtendedAdapter(response.body(), getApplicationContext()));
                    from.setFocusable(false);
                    to.setFocusable(false);
                    mBtn.setFocusable(false);
                }else{
                    Toast.makeText(SearchArtikalByOcena.this, "Нађено резултата " + response.body().size(), Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchArtikalByOcena.this);
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SearchArtikalByOcena.this, SearchMenuActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setMessage("Нема резултата");
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