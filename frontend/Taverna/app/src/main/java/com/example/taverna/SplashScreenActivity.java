package com.example.taverna;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.taverna.servisi.ServiceUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);
        int SPLASH_TIME_OUT = 3000;


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bbbbbbbbbbbbbb " +  sharedPreferences.getString(MainActivity.Username, ""));

                if(!sharedPreferences.getString(MainActivity.Username, "").equals("")){
                    ServiceUtil.setToken(sharedPreferences.getString(MainActivity.Token, ""));
                    startActivity(new Intent(SplashScreenActivity.this, GlavnaStranaActivity.class));
                }else {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
                finish(); // da ne bi mogao da ode back na splash
            }
        }, SPLASH_TIME_OUT);
    }

}
