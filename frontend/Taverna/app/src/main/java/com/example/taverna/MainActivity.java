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
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.ServiceUtil;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;




    public static final String MyPres = "MyPre";
    public static final String Username = "usernameKey";
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


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;



    }
    private final SensorEventListener mSensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 10) {
             //   Toast.makeText(getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
                pozoviLogin();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void pozoviLogin(){
        String korisnicko = usernameEditText.getText().toString();
        String sifra = passwordEditText.getText().toString();

        if(validate(korisnicko,sifra)){
            doLogin(korisnicko,sifra);

        }
    }

    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }


    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
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


        korisniciApiService = ServiceUtil.korisniciApiService;
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
                    ServiceUtil.setToken(korisnik.getToken());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Username,korisnik.getKorisnicko());
                    editor.putString(Role,korisnik.getRole());
                    editor.putInt(String.valueOf(ID),korisnik.getId());
                    editor.commit();
                    startActivity(intent);


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