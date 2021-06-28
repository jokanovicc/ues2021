package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taverna.adapter.DodavanjeAkcijeAdapter;
import com.example.taverna.model.AkcijaDodaj;
import com.example.taverna.model.Artikal;
import com.example.taverna.model.Komentar;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DodavanjeAkcije extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView mTv;
    Button mBtn;
    TextView mTv2;
    Button mBtn2;
    Calendar c;
    DatePickerDialog dpd;
    private  EditText popust;
    private Button zavrsi;
    private RecyclerView recyclerView;
    private ArtikliApiService artikliApiService;
    SharedPreferences sharedPreferences;


    String datum;
    String datum2;

    EditText opis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_akcije);


        sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);

        mTv =findViewById(R.id.txt_datum);
        mBtn = findViewById(R.id.btnDatum1);
        mTv2 =findViewById(R.id.txt_datum2);
        mBtn2 = findViewById(R.id.btnDatum2);

        popust = findViewById(R.id.popustAkcija);
        zavrsi = findViewById(R.id.dodajAkcijuButton);
        opis = findViewById(R.id.opisAkcije);

        recyclerView = findViewById(R.id.rvArtikliAkcija);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        zavrsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajAkciju();
            }
        });


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                dpd = new DatePickerDialog(DodavanjeAkcije.this,
                        new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                            {
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, monthOfYear);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                String mesec;
                                String dan;
                                if((monthOfYear+1)<10){
                                    mesec = "0"+(monthOfYear+1);
                                }else{
                                    mesec = ""+(monthOfYear+1);
                                }
                                if(dayOfMonth<10){
                                    dan = "0" + dayOfMonth;

                                }else{
                                    dan = ""+dayOfMonth;
                                }

                                datum = year + "-" + mesec + "-" + dan;
                                mTv.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                                Toast.makeText(DodavanjeAkcije.this, dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_LONG).show();
                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();



//                c= Calendar.getInstance();
//                int day = c.get(Calendar.DAY_OF_MONTH);
//                int month = c.get(Calendar.MONTH);
//                int year = c.get(Calendar.YEAR);
//
//                dpd = new DatePickerDialog(DodavanjeAkcije.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDayOfMonth) {
//                        if((mMonth+1)<10){
//                            mTv.setText(mYear+"-"+"0"+(mMonth+1)+"-"+mDayOfMonth);
//                            datum2 = mYear + "-" +"0"+ (mMonth + 1) + "-" + mDayOfMonth;
//                        }else {
//                            mTv.setText(mYear + "-" + (mMonth + 1) + "-" + mDayOfMonth);
//                            datum2 = mYear + "-" + (mMonth + 1) + "-" + mDayOfMonth;
//                        }
//
//                    }
//
//
//                }, day,month,year);
//                dpd.show();


            }
        });



        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                dpd = new DatePickerDialog(DodavanjeAkcije.this,
                        new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                            {
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, monthOfYear);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                String mesec;
                                String dan;
                                if((monthOfYear+1)<10){
                                    mesec = "0"+(monthOfYear+1);
                                }else{
                                    mesec = ""+(monthOfYear+1);
                                }
                                if(dayOfMonth<10){
                                    dan = "0" + dayOfMonth;

                                }else{
                                    dan = ""+dayOfMonth;
                                }

                                datum2 = year + "-" + mesec + "-" + dan;
                                mTv2.setText(year+"-"+monthOfYear+"-"+dayOfMonth);


                                Toast.makeText(DodavanjeAkcije.this, dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_LONG).show();
                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getArtikli();
    }


    private void dodajAkciju(){
        String procenat = popust.getText().toString();
        String opisAkcije = opis.getText().toString();
        AkcijaDodaj akcijaDodaj = new AkcijaDodaj();


        if(DodavanjeAkcijeAdapter.artikli.isEmpty()){
            Toast.makeText(DodavanjeAkcije.this, "Morate dodati bar jedan artikal", Toast.LENGTH_LONG).show();
        }
        else {

            if (opisAkcije.length() == 0) {
                opis.requestFocus();
                opis.setError("Morate uneti opis");
            } else {

                if (datum == null) {
                    Toast.makeText(DodavanjeAkcije.this, "Morate uneti pocetni datum", Toast.LENGTH_LONG).show();
                } else {

                    if (datum2 == null) {
                        Toast.makeText(DodavanjeAkcije.this, "Morate uneti zavrsni datum", Toast.LENGTH_LONG).show();

                    } else {


                        if (procenat.length() == 0 || Integer.parseInt(procenat) > 99) {
                            popust.requestFocus();
                            popust.setError("Morate uneti popust");

                        } else {
                            akcijaDodaj.setProcenat(Integer.parseInt(procenat));
                            akcijaDodaj.setOdKad(datum);
                            akcijaDodaj.setDoKad(datum2);
                            akcijaDodaj.setOpis(opisAkcije);
                            akcijaDodaj.setArtikli(DodavanjeAkcijeAdapter.artikli);

                            artikliApiService = RetrofitClient.artikliApiService;
                            Call<Void> call = artikliApiService.dodajAkciju(akcijaDodaj);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    DodavanjeAkcijeAdapter.artikli.clear();
                                    System.out.println("Okej");
                                    Intent intent2 = new Intent(DodavanjeAkcije.this, Akcije.class);
                                    startActivity(intent2);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    System.out.println("nije okej");

                                }
                            });
                        }
                    }
                }
            }


        }


    }




    private void getArtikli(){
        artikliApiService = RetrofitClient.artikliApiService;
        Call<List<Artikal>> call = artikliApiService.getArtikliProdavaca(sharedPreferences.getInt(String.valueOf(MainActivity.ID),1));
        call.enqueue(new Callback<List<Artikal>>() {
            @Override
            public void onResponse(Call<List<Artikal>> call, Response<List<Artikal>> response) {
                recyclerView.setAdapter(new DodavanjeAkcijeAdapter(response.body(),getApplicationContext()));
            }
            @Override
            public void onFailure(Call<List<Artikal>> call, Throwable t) {

            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}