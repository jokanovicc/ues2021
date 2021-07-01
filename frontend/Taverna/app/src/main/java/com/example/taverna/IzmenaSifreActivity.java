package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taverna.model.IzmenaSifreDTO;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IzmenaSifreActivity extends AppCompatActivity {


    static final String TAG = IzmenaSifreActivity.class.getSimpleName();
    private Button izmeni;
    private KorisniciApiService korisniciApiService;
    private EditText staraSifraEdit;
    private EditText novaSifraEdit;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena_sifre);

        staraSifraEdit = findViewById(R.id.staraSifra);
        novaSifraEdit = findViewById(R.id.novaSifra);
        izmeni = findViewById(R.id.editSifre);


        izmeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izmeniSifru();
            }
        });




    }




    public void izmeniSifru(){

        String staraSifraIzmena = staraSifraEdit.getText().toString();
        String novaSifraIzmena = novaSifraEdit.getText().toString();
        System.out.println(staraSifraIzmena);

        if(staraSifraIzmena.length()==0){
            staraSifraEdit.requestFocus();
            staraSifraEdit.setError("Нека поља су празна");
        }else if(novaSifraIzmena.length()==0) {
            novaSifraEdit.requestFocus();
            novaSifraEdit.setError("Нека поља су празна");
        }
        else {


            IzmenaSifreDTO izmenaSifreDTO = new IzmenaSifreDTO();
            izmenaSifreDTO.setNovaSifra(novaSifraIzmena);
            izmenaSifreDTO.setStaraSifra(staraSifraIzmena);

            korisniciApiService = RetrofitClient.korisniciApiService;
            Call<Boolean> call = korisniciApiService.izmeniSifru(izmenaSifreDTO);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.body()) {
                        Toast.makeText(IzmenaSifreActivity.this, "Успешно измењена шифра", Toast.LENGTH_SHORT).show();

                    } else if (!response.body()) {
                        Toast.makeText(IzmenaSifreActivity.this, "То вам није стара шифра", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(IzmenaSifreActivity.this, "Грешка са сервером", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}