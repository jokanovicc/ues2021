package com.example.taverna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.R;
import com.example.taverna.model.Korisnik;
import com.example.taverna.servisi.KorisniciApiService;
import com.example.taverna.servisi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KorisniciAdapter extends RecyclerView.Adapter<KorisniciAdapter.ViewHolder> {
    private List<Korisnik> mData;
    private Context context;
    private Button button;
    private KorisniciApiService korisniciApiService;

    public KorisniciAdapter(List<Korisnik> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView korisnicko;
        Switch dugme;


        ViewHolder(View itemView) {
            super(itemView);
            korisnicko = itemView.findViewById(R.id.korisnickoBlokiranje);
            dugme = itemView.findViewById(R.id.blokiranje);

        }
    }

    @Override
    public KorisniciAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.korisnici_row, parent, false);
        return new KorisniciAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KorisniciAdapter.ViewHolder holder, int position) {
        Korisnik korisnik = mData.get(position);
        holder.korisnicko.setText(korisnik.getKorisnicko());

        if(korisnik.getBlokiran()==true){
            holder.dugme.setChecked(true);
        }

        holder.dugme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                blokirajKorisnika(korisnik.getId());
                Toast.makeText(context, "ЧИНИЧНО СТАЊЕ КОРИСНИКА ПРОМЕЊЕНО", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void blokirajKorisnika(int id){
        korisniciApiService = RetrofitClient.korisniciApiService;
        Call<Korisnik> call = korisniciApiService.blokirajKorisnika(id);
        call.enqueue(new Callback<Korisnik>() {
            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                Toast.makeText(context, "УЧИЊЕНО НЕКО ДЕЛО", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {

            }
        });

    }
}


