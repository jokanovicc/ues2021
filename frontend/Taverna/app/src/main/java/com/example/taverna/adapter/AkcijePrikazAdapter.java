package com.example.taverna.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.GlavnaStranaActivity;
import com.example.taverna.ProdavacGlavnaActivity;
import com.example.taverna.R;
import com.example.taverna.model.AkcijaPrikaz;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkcijePrikazAdapter extends RecyclerView.Adapter<AkcijePrikazAdapter.ViewHolder> {
    private List<AkcijaPrikaz> mData;
    private Context context;
    private Button button;
    private ArtikliApiService artikliApiService;

    public static final String MyPres = "MyPre1";
    public static final String Username = "usernameKey";
    public static final Integer ID = 2;


    public AkcijePrikazAdapter(List<AkcijaPrikaz> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView artikli;
        TextView popust;
        TextView doKad;
        TextView odKad;
        TextView opis;
        Button obrisi;


        ViewHolder(View itemView) {
            super(itemView);
            artikli = itemView.findViewById(R.id.prikazAkcijeArtikli);
            popust = itemView.findViewById(R.id.prikazPopust);
            doKad = itemView.findViewById(R.id.prikazDoKad);
            odKad = itemView.findViewById(R.id.prikazOdKad);
            opis = itemView.findViewById(R.id.opisPopust);
            obrisi = (Button)itemView.findViewById(R.id.obrisiAkcijuButton);


        }
    }

    @Override
    public AkcijePrikazAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.akcije_row, parent, false);
        return new AkcijePrikazAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AkcijePrikazAdapter.ViewHolder holder, int position) {
        AkcijaPrikaz akcijaPrikaz = mData.get(position);


        holder.artikli.setText(akcijaPrikaz.getArtikli());
        holder.popust.setText("Попуст: "+akcijaPrikaz.getProcenat());
        holder.doKad.setText("До: " + akcijaPrikaz.getDoKad());
        holder.odKad.setText("Од: " + akcijaPrikaz.getOdKad());
        holder.opis.setText(akcijaPrikaz.getTekst());

        holder.obrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obrisiAkciju(akcijaPrikaz.getId());
                Intent intent = new Intent(context, GlavnaStranaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void obrisiAkciju(Integer id){
        artikliApiService = RetrofitClient.artikliApiService;
        Call<Void> call = artikliApiService.deleteAkcije(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Obrisan");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("nije obrisan");

            }
        });
    }
}
