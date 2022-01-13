package com.example.taverna.elastic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.R;
import com.example.taverna.elastic.dto.PorudzbinaESDTO;

import java.util.List;

public class PorudzbinaExtendedAdapter extends RecyclerView.Adapter<PorudzbinaExtendedAdapter.ViewHolder> {
    private List<PorudzbinaESDTO> mData;
    private Context context;
    private Button button;

    public PorudzbinaExtendedAdapter(List<PorudzbinaESDTO> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView satnica;
        TextView kupac;
        TextView cena;


        ViewHolder(View itemView) {
            super(itemView);
            satnica = itemView.findViewById(R.id.extendedSatnica);
            kupac = itemView.findViewById(R.id.extendedKupac);
            cena = itemView.findViewById(R.id.extendedCenaPorudzbine);

        }
    }

    @Override
    public PorudzbinaExtendedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.porudzbina_extended_row, parent, false);
        return new PorudzbinaExtendedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PorudzbinaExtendedAdapter.ViewHolder holder, int position) {
        PorudzbinaESDTO porucivanjeDTO = mData.get(position);
        holder.satnica.setText(porucivanjeDTO.getSatnica());
        holder.cena.setText(porucivanjeDTO.getCena().toString());
        holder.kupac.setText(porucivanjeDTO.getKupac());




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
