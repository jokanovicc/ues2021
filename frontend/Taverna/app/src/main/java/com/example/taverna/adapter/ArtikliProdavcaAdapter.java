package com.example.taverna.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.GlavnaStranaActivity;
import com.example.taverna.JeloAktiviti;
import com.example.taverna.Komentari;
import com.example.taverna.MainActivity;
import com.example.taverna.ProdavacGlavnaActivity;
import com.example.taverna.Prodavci;
import com.example.taverna.R;
import com.example.taverna.model.Artikal;
import com.example.taverna.model.PorucivanjeDTO;
import com.example.taverna.model.StavkaDTO;
import com.example.taverna.servisi.ArtikliApiService;
import com.example.taverna.servisi.PorudzbineApiService;
import com.example.taverna.servisi.ServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikliProdavcaAdapter extends RecyclerView.Adapter<ArtikliProdavcaAdapter.ViewHolder> {
    private List<Artikal> mData;
    private Context context;
    private Button button;
    private String m_Text = "";
    Button button2;
    private ArtikliApiService artikliApiService;


    public ArtikliProdavcaAdapter (List<Artikal> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView opis;
        TextView naziv;
        TextView cena;
        ImageView slika;
        Button obrisi;


        ViewHolder(View itemView) {
            super(itemView);

            naziv = itemView.findViewById(R.id.nazivArtikla1);
            opis = itemView.findViewById(R.id.opisArtikla1);
            cena = itemView.findViewById(R.id.cena1);
            slika = itemView.findViewById(R.id.slikaArtikla1);
            obrisi = itemView.findViewById(R.id.obrisiProizvod1);

        }
    }

    @Override
    public ArtikliProdavcaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artikli_prodavca_row, parent, false);
        return new ArtikliProdavcaAdapter .ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ArtikliProdavcaAdapter.ViewHolder holder, int position) {
        Artikal artikal = mData.get(position);
        holder.naziv.setText(artikal.getNaziv());
        holder.opis.setText(artikal.getOpis());
        holder.cena.setText(artikal.getCena().toString());
        if(artikal.getPhoto()!=null)
            holder.slika.setImageBitmap(artikal.getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JeloAktiviti.class);
                Bundle b = new Bundle();
                b.putInt("ID",artikal.getId());
                intent.putExtras(b);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.obrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obrisiProizvod(artikal.getId());
                Intent intent = new Intent(context, ProdavacGlavnaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    private void obrisiProizvod(int id){


        artikliApiService = ServiceUtil.artikliApiService;
        Call<Artikal> call = artikliApiService.obrisiArtikal(id);

        call.enqueue(new Callback<Artikal>() {

            @Override
            public void onResponse(Call<Artikal> call, Response<Artikal> response) {
                notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Artikal> call, Throwable t) {

            }
        });




    }



    @Override
    public int getItemCount() {
        return mData.size();
    }



}

