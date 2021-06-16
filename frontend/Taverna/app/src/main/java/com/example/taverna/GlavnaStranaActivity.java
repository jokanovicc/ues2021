package com.example.taverna;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.adapter.ProdavciListaAdapter;
import com.example.taverna.model.Prodavac;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.ServiceUtil;
import com.example.taverna.ui.porudzbine.PorudzbinaViewModel;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GlavnaStranaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button dugme;
    private ImageView profil;
    private Toolbar toolbar;
    private String username;
    private String rola;
    private int id;
    Menu menu;
    MenuInflater menuInflater;
    MenuItem menuItem;
    private RecyclerView recyclerView;

    private KorisniciApiService korisniciApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        TextView userName = findViewById(R.id.userName);
        TextView uloga = findViewById(R.id.rola);


        recyclerView = findViewById(R.id.rvProdavciListaPocetna);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        drawerLayout = findViewById(R.id.drawer_layout);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPres,Context.MODE_PRIVATE);

        String userPref = sharedPreferences.getString(MainActivity.Username, "");
        String rola1 = sharedPreferences.getString(MainActivity.Role,"");
        int id = sharedPreferences.getInt(String.valueOf(MainActivity.ID),1);
        userName.setText(userPref);
        uloga.setText(rola1);




        NavigationView navigationView = findViewById(R.id.nav_view);


        if(!rola1.equals("KUPAC")){
            recyclerView.setAlpha(0);
        }



        if(rola1.equals("PRODAVAC")){
            navigationView.getMenu().findItem(R.id.nav_poruzbina).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_korisnici).setVisible(false);

        }

        if(rola1.equals("KUPAC")){
            navigationView.getMenu().findItem(R.id.nav_korisnici).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_home).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_artikli).setVisible(false);

        }


        if(rola1.equals("ADMIN")){
            navigationView.getMenu().findItem(R.id.nav_home).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_artikli).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_poruzbina).setVisible(false);
        }









       // navigationView.getMenu().findItem(R.id.nav_home).setVisible(false);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.nav_home){
                    Intent i = new Intent(GlavnaStranaActivity.this, Komentari.class);
                    startActivity(i);
                }

                if(item.getItemId()==R.id.nav_poruzbina){
                    Intent i5 = new Intent(GlavnaStranaActivity.this, Porudzbine.class);
                    startActivity(i5);
                }


                if(item.getItemId()==R.id.nav_korisnici){
                    Intent i4 = new Intent(GlavnaStranaActivity.this, Korisnici.class);
                    startActivity(i4);
                }

                if(item.getItemId()==R.id.nav_artikli){
                    Intent i4 = new Intent(GlavnaStranaActivity.this, ProdavacGlavnaActivity.class);
                    startActivity(i4);
                }

                if(item.getItemId()==R.id.nav_gallery){
                    Intent i2 = new Intent(GlavnaStranaActivity.this, Akcije.class);
                    startActivity(i2);
                }
                if(item.getItemId()==R.id.nav_slideshow){
                    Intent i3 = new Intent(GlavnaStranaActivity.this, ProfilKorisnika.class);
                    startActivity(i3);

                }

                if(item.getItemId()==R.id.nav_odjava){
                    ServiceUtil.setToken("");
                    SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.MyPres, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.clear();
                    editor.apply();
                    finish();
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA " +  sharedPreferences1.getString(MainActivity.Username, ""));

                    Intent i6 = new Intent(GlavnaStranaActivity.this, MainActivity.class);
                    startActivity(i6);
                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getProdavci();

    }

    private void getProdavci(){

        korisniciApiService = ServiceUtil.korisniciApiService;
        Call<List<Prodavac>> call = korisniciApiService.getProdavci();
        call.enqueue(new Callback<List<Prodavac>>() {
            @Override
            public void onResponse(Call<List<Prodavac>> call, Response<List<Prodavac>> response) {
                recyclerView.setAdapter(new ProdavciListaAdapter(response.body(), getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Prodavac>> call, Throwable t) {

            }
        });


    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}
