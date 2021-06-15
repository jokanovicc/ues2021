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

import com.example.taverna.DodavanjeRecenzije;
import com.example.taverna.Komentari;
import com.example.taverna.Porudzbine;
import com.example.taverna.Prodavci;
import com.example.taverna.R;
import com.example.taverna.model.Komentar;
import com.example.taverna.model.PorudzbinePrikaz;
import com.example.taverna.servisi.PorudzbineApiService;
import com.example.taverna.servisi.ServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PorudzbineAdapter extends RecyclerView.Adapter<PorudzbineAdapter.ViewHolder> {
    private List<PorudzbinePrikaz> mData;
    private Context context;
    private Button button;
    PorudzbineApiService porudzbineApiService;

    public PorudzbineAdapter(List<PorudzbinePrikaz> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView artikli;
        TextView datum;
        Button button;


        ViewHolder(View itemView) {
            super(itemView);

            artikli = itemView.findViewById(R.id.tekstArtikli);
            datum = itemView.findViewById(R.id.porudzbinaSatnica);
            button = (Button) itemView.findViewById(R.id.porudzbinaDugme);

        }
    }

    @Override
    public PorudzbineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.porudzbine_row, parent, false);
        return new PorudzbineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PorudzbineAdapter.ViewHolder holder, int position) {
        PorudzbinePrikaz porudzbina = mData.get(position);

        holder.artikli.setText(porudzbina.getArtikli());
        holder.datum.setText(porudzbina.getSatnica());
        Button dugme = holder.button;

        if(!porudzbina.getDostavljeno()){
            dugme.setText("није стигло-јави");
            dugme.setEnabled(true);
            dugme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stigloJe(porudzbina.getId());
                    notifyDataSetChanged();
                    Intent intent = new Intent(context, Porudzbine.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }else {
            dugme.setText("стигло-рецензирај");
            dugme.setEnabled(true);
            dugme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DodavanjeRecenzije.class);
                    Bundle b = new Bundle();
                    b.putInt("ID",porudzbina.getId());
                    intent.putExtras(b);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public void stigloJe(int id){

        porudzbineApiService = ServiceUtil.porudzbineApiService;
        Call<PorudzbinePrikaz> call = porudzbineApiService.getStiglo(id);
        call.enqueue(new Callback<PorudzbinePrikaz>() {
            @Override
            public void onResponse(Call<PorudzbinePrikaz> call, Response<PorudzbinePrikaz> response) {
                Toast.makeText(context, "СТИГЛОООО", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PorudzbinePrikaz> call, Throwable t) {

            }
        });




    }
}
