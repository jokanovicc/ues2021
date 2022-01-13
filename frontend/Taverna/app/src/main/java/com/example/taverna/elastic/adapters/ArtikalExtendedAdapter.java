package com.example.taverna.elastic.adapters;

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

public class ArtikalExtendedAdapter extends RecyclerView.Adapter<ArtikalExtendedAdapter.ViewHolder> {
    private List<ArtikalESDTO> mData;
    private Context context;
    private Button button;

    public ArtikalExtendedAdapter(List<ArtikalESDTO> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView naziv;
        TextView komentara;
        TextView ocena;
        TextView cena;


        ViewHolder(View itemView) {
            super(itemView);
            naziv = itemView.findViewById(R.id.extendedArtikalNaziv);
            cena = itemView.findViewById(R.id.extendedCena);
            ocena = itemView.findViewById(R.id.extendedOcena);
            komentara = itemView.findViewById(R.id.extendedBrojKomentara);

        }
    }

    @Override
    public ArtikalExtendedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artikli_extender_row, parent, false);
        return new ArtikalExtendedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtikalExtendedAdapter.ViewHolder holder, int position) {
        ArtikalESDTO artikalESDTO = mData.get(position);
        holder.cena.setText(artikalESDTO.getCena().toString());
        holder.naziv.setText(artikalESDTO.getNaziv());
        holder.komentara.setText(artikalESDTO.getKomentara().toString());
        holder.ocena.setText(artikalESDTO.getRating().toString());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
