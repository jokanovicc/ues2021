package com.example.taverna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.R;
import com.example.taverna.elastic.dto.ArtikalESDTO;

import java.util.List;


public class ElasticSearchAdapter extends RecyclerView.Adapter<ElasticSearchAdapter.ViewHolder> {
    private List<ArtikalESDTO> mData;
    private Context context;
    private Button button;

    public ElasticSearchAdapter(List<ArtikalESDTO> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView naziv;
        TextView opis;
        TextView cena;


        ViewHolder(View itemView) {
            super(itemView);
            naziv = itemView.findViewById(R.id.nazivArtiklaSearch);
            opis = itemView.findViewById(R.id.opisArtiklaSearch);
            cena = itemView.findViewById(R.id.cenaArtiklaSearch);

        }
    }

    @Override
    public ElasticSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elastic_naziv_row, parent, false);
        return new ElasticSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElasticSearchAdapter.ViewHolder holder, int position) {
        ArtikalESDTO artikalESDTO = mData.get(position);
        holder.cena.setText(artikalESDTO.getCena().toString());
        holder.naziv.setText(artikalESDTO.getNaziv());
        holder.opis.setText(artikalESDTO.getOpis());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

