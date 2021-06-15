package com.example.taverna.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.GlavnaStranaActivity;
import com.example.taverna.JeloAktiviti;
import com.example.taverna.Komentari;
import com.example.taverna.Korisnici;
import com.example.taverna.R;
import com.example.taverna.model.Artikal;
import com.example.taverna.model.Komentar;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.ServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KomentariUpravljanjeAdapter extends RecyclerView.Adapter<KomentariUpravljanjeAdapter.ViewHolder> {
    private List<Komentar> mData;
    private Context context;
    private ArtikliApiService artikliApiService;

    public KomentariUpravljanjeAdapter(List<Komentar> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView komentator;
        TextView komentar;
        TextView ocena;
        Button button;


        ViewHolder(View itemView) {
            super(itemView);
            komentator = itemView.findViewById(R.id.uprKomentator);
            komentar = itemView.findViewById(R.id.uprKomentar);
            ocena = itemView.findViewById(R.id.uprOcena);
            button = (Button) itemView.findViewById(R.id.uprArhiviraj);

        }
    }




    @Override
    public KomentariUpravljanjeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.komentar_upr_row, parent, false);
        return new KomentariUpravljanjeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KomentariUpravljanjeAdapter.ViewHolder holder, int position) {
        Komentar komentar = mData.get(position);

        if(komentar.getAnoniman()){
            holder.komentator.setText("АНОНИМАН");

        }else{
            holder.komentator.setText(komentar.getKupac());

        }
        holder.komentar.setText(komentar.getKomentar());
        holder.ocena.setText(komentar.getOcena().toString());
        Button button1 = holder.button;
        button1.setText("Архивирај");
        button1.setEnabled(true);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arhivirajKomentar(komentar.getId());
                Intent intent = new Intent(context, Komentari.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void arhivirajKomentar(int id){

        artikliApiService = ServiceUtil.artikliApiService;
        Call<Komentar> call = artikliApiService.arhivirajKomentar(id);
        call.enqueue(new Callback<Komentar>() {
            @Override
            public void onResponse(Call<Komentar> call, Response<Komentar> response) {
                Toast.makeText(context, "АРХИВИРАН", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Komentar> call, Throwable t) {

            }
        });




    }


}
