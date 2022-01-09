package com.example.taverna.servisi;

import android.graphics.Bitmap;

import com.example.taverna.util.SerijalizacijaSlike;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String SERVICE_API_PATH = "http://192.168.1.10:8080/";
    public static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        RetrofitClient.token = token;
    }

    static Gson gson = new GsonBuilder().registerTypeAdapter(Bitmap.class, SerijalizacijaSlike.getBitmapTypeAdapter())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();



    public static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();


    public static Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();



    public static ArtikliApiService artikliApiService = retrofit.create(ArtikliApiService.class);
    public static KorisniciApiService korisniciApiService = retrofit.create(KorisniciApiService.class);
    public static PorudzbineApiService porudzbineApiService = retrofit.create(PorudzbineApiService.class);
    public static ElasticSearchAPIService elasticSearchAPIService = retrofit.create(ElasticSearchAPIService.class);



}
