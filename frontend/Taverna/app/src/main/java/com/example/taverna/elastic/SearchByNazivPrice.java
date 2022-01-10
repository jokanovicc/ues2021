package com.example.taverna.elastic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.R;
import com.example.taverna.adapter.ElasticSearchAdapter;
import com.example.taverna.elastic.dto.AndOrRequest;
import com.example.taverna.elastic.dto.ArtikalESDTO;
import com.example.taverna.elastic.dto.ToFromRequestDTO;
import com.example.taverna.servisi.ElasticSearchAPIService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchByNazivPrice extends AppCompatActivity {

    Button mBtn;
    private RecyclerView recyclerView;
    private ElasticSearchAPIService elasticSearchAPIService;
    private EditText from;
    private EditText to;
    private EditText naziv;
    private List<ArtikalESDTO> artikalESDTOS;
    private Switch aSwitch;
    private Boolean isOr = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_naziv_price);


        mBtn = findViewById(R.id.searchPriceNazivButton);
        from = findViewById(R.id.inputPriceCombinedFrom);
        to = findViewById(R.id.inputPriceCombinedTo);
        naziv = findViewById(R.id.inputNazivCombinedSearch);
        aSwitch = findViewById(R.id.orAndSwitch);

        recyclerView = findViewById(R.id.rvNazivPrice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        artikalESDTOS = new ArrayList<>();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiFormu();
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isOr = !isOr;
                Toast.makeText(SearchByNazivPrice.this, "Izmenjem rezim pretrage sad je " + isOr, Toast.LENGTH_SHORT).show();

            }
        });

        System.out.println(isOr);





    }

    private void posaljiFormu(){
        Integer fromPrice = Integer.parseInt(from.getText().toString());
        Integer toPrice = Integer.parseInt(to.getText().toString());
        String nazivArtikal = naziv.getText().toString();



        ToFromRequestDTO toFromRequestDTO = new ToFromRequestDTO();
        toFromRequestDTO.setFrom(fromPrice);
        toFromRequestDTO.setTo(toPrice);

        AndOrRequest andOrRequest = new AndOrRequest();
        andOrRequest.setFrom(fromPrice);
        andOrRequest.setTo(toPrice);
        andOrRequest.setNaziv(nazivArtikal);
        andOrRequest.setOr(isOr);


        elasticSearchAPIService = RetrofitClient.elasticSearchAPIService;
        Call<List<ArtikalESDTO>> call = elasticSearchAPIService.searchByNazivCena(andOrRequest);

            call.enqueue(new Callback<List<ArtikalESDTO>>() {
                @Override
                public void onResponse(Call<List<ArtikalESDTO>> call, Response<List<ArtikalESDTO>> response) {
                    System.out.println(response.body());

                    if(response.body().size()!=0){
                        Toast.makeText(SearchByNazivPrice.this, "Нађено резултата " + response.body().size(), Toast.LENGTH_SHORT).show();
                        recyclerView.setAdapter(new ElasticSearchAdapter(response.body(), getApplicationContext()));
                        from.setFocusable(false);
                        to.setFocusable(false);
                        naziv.setFocusable(false);
                        mBtn.setAlpha(0);
                    }else{
                        Toast.makeText(SearchByNazivPrice.this, "Нађено резултата " + response.body().size(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchByNazivPrice.this);
                        builder.setCancelable(true);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchByNazivPrice.this, SearchMenuActivity.class);
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
