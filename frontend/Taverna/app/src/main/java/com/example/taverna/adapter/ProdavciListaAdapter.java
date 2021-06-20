package com.example.taverna.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.IzabraniProdavacActivity;
import com.example.taverna.R;
import com.example.taverna.model.Prodavac;

import java.util.List;

public class ProdavciListaAdapter extends RecyclerView.Adapter<ProdavciListaAdapter.ViewHolder> {
    private List<Prodavac> mData;
    private Context context;
    private Button button;

    public ProdavciListaAdapter(List<Prodavac> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView naziv;
        TextView datum;
        TextView adresa;
        TextView ocena;


        ViewHolder(View itemView) {
            super(itemView);
            naziv = itemView.findViewById(R.id.naziv);
            datum = itemView.findViewById(R.id.datum);
            adresa = itemView.findViewById(R.id.adresa);
            ocena = itemView.findViewById(R.id.ocenaProdavca);
        }
    }

    @Override
    public ProdavciListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prodavci_row, parent, false);
        return new ProdavciListaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProdavciListaAdapter.ViewHolder holder, int position) {
        Prodavac prodavac = mData.get(position);
        holder.naziv.setText(prodavac.getNaziv());
        holder.datum.setText(prodavac.getPoslujeOd());
        holder.adresa.setText(prodavac.getAdresa());
        holder.ocena.setText(prodavac.getOcena().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IzabraniProdavacActivity.class);
                Bundle b = new Bundle();
                b.putInt("ID",prodavac.getId());
                intent.putExtras(b);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

