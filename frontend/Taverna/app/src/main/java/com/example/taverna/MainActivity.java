package com.example.taverna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taverna.model.Korisnik;
import com.example.taverna.model.Login;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private Button registerProdavacButton;
    private Button prodavacButton;
    private KorisniciApiService korisniciApiService;

    private SharedPreferences sharedPreferences;
    static final String TAG = RegisterActivity.class.getSimpleName();
    private RecyclerView recyclerView;



    public static final String MyPres = "MyPre";
    public static final String Username = "usernameKey";
    public static final String Token = "tokenKey";
    public static final String Role = "role";
    public static final Integer ID = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.activity_main_usernameEditText);
        passwordEditText = findViewById(R.id.activity_main_passwordEditText);
        loginButton = findViewById(R.id.activity_main_loginButton);
        registerButton = findViewById(R.id.activity_main_registerButton);
        registerProdavacButton = findViewById(R.id.registerProdavac2);

        sharedPreferences = getSharedPreferences(MyPres, Context.MODE_PRIVATE);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String korisnicko = usernameEditText.getText().toString();
                String sifra = passwordEditText.getText().toString();

                if(validate(korisnicko,sifra)){
                    doLogin(korisnicko,sifra);

                }


            }
        });

        registerProdavacButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, RegisterProdavac.class);
                startActivity(intent3);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
                Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent2);

            }
        });




    }


    private void pozoviLogin(){
        String korisnicko = usernameEditText.getText().toString();
        String sifra = passwordEditText.getText().toString();

        if(validate(korisnicko,sifra)){
            doLogin(korisnicko,sifra);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }





    public boolean validate(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Поље корисничко име не сме бити празно!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Поље лозинка не сме бити празно!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    public void doLogin(final String username, final String password){


        korisniciApiService = RetrofitClient.korisniciApiService;
        Login login = new Login();
        login.setKorisnicko(username);
        login.setSifra(password);
        Call<Korisnik> call = korisniciApiService.login(login);
        call.enqueue(new Callback<Korisnik>() {
            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                if(response.body()!=null) {
                    Korisnik korisnik = response.body();
                    Intent intent = new Intent(MainActivity.this, GlavnaStranaActivity.class);
                    RetrofitClient.setToken(korisnik.getToken());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Token,korisnik.getToken());
                    editor.putString(Username,korisnik.getKorisnicko());
                    editor.putString(Role,korisnik.getRole());
                    editor.putInt(String.valueOf(ID),korisnik.getId());
                    editor.commit();
                    startActivity(intent);
                    finish();



                }else{
                    Toast.makeText(MainActivity.this, "Неисправни логин подаци", Toast.LENGTH_SHORT).show();
                    usernameEditText.requestFocus();
                    usernameEditText.setError("Погрешни креденцијали лозинки или корисничком имена");
                    passwordEditText.requestFocus();

                }
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Greska", Toast.LENGTH_SHORT).show();

            }
        });

    }



}