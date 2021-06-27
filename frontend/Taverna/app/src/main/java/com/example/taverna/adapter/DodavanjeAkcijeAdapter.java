package com.example.taverna.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.DodavanjeAkcije;
import com.example.taverna.R;
import com.example.taverna.model.AkcijaDodaj;
import com.example.taverna.model.Artikal;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DodavanjeAkcijeAdapter extends RecyclerView.Adapter<DodavanjeAkcijeAdapter.ViewHolder> {
    private List<Artikal> mData;
    private Context context;
    public static List<Integer> artikli = new ArrayList<>();


    public DodavanjeAkcijeAdapter(List<Artikal> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView artikli;
        Button button;



        ViewHolder(View itemView) {
            super(itemView);
            artikli = itemView.findViewById(R.id.nazivArtiklaZaAkciju);
            button = (Button)itemView.findViewById(R.id.dodajNaAkciju);


        }
    }

    @Override
    public DodavanjeAkcijeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artikli_akcije_dodavanje_row, parent, false);
        return new DodavanjeAkcijeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DodavanjeAkcijeAdapter.ViewHolder holder, int position) {
        Artikal artikal = mData.get(position);
        holder.artikli.setText(artikal.getNaziv());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.button.setEnabled(false);
                System.out.println(artikal.getId());
                artikli.add(artikal.getId());
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}