package com.example.taverna.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.JeloAktiviti;
import com.example.taverna.R;
import com.example.taverna.model.Komentar;

import java.util.List;

public class KomentariAdapter extends RecyclerView.Adapter<KomentariAdapter.ViewHolder> {
    private List<Komentar> mData;
    private Context context;
    private Button button;

    public KomentariAdapter(List<Komentar> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView komentator;
        TextView komentar;
        TextView ocena;


        ViewHolder(View itemView) {
            super(itemView);
            komentator = itemView.findViewById(R.id.komentatorKomentara);
            komentar = itemView.findViewById(R.id.komentarKomentar);
            ocena = itemView.findViewById(R.id.ocenaKomentar);

        }
    }

    @Override
    public KomentariAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.komentari_row, parent, false);
        return new KomentariAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KomentariAdapter.ViewHolder holder, int position) {
        Komentar komentar = mData.get(position);

        if(komentar.getAnoniman()){
            holder.komentator.setText("АНОНИМАН");

        }else{
            holder.komentator.setText(komentar.getKupac());

        }

        holder.komentar.setText(komentar.getKomentar());
        holder.ocena.setText(komentar.getOcena().toString());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

