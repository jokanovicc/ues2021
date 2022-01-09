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
import com.example.taverna.elastic.dto.PorudzbinaESDTO;
import com.example.taverna.model.PorucivanjeDTO;

import java.util.List;

public class PorudzbinaSearchAdapter extends RecyclerView.Adapter<com.example.taverna.elastic.adapters.PorudzbinaSearchAdapter.ViewHolder> {
    private List<PorudzbinaESDTO> mData;
    private Context context;
    private Button button;

    public PorudzbinaSearchAdapter(List<PorudzbinaESDTO> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView komentar;
        TextView satnica;
        TextView anoniman;


        ViewHolder(View itemView) {
            super(itemView);
            komentar = itemView.findViewById(R.id.komentarSearch);
            satnica = itemView.findViewById(R.id.satnicaSearch);
            anoniman = itemView.findViewById(R.id.anonimanSearch);

        }
    }

    @Override
    public com.example.taverna.elastic.adapters.PorudzbinaSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elastic_komentar_row, parent, false);
        return new com.example.taverna.elastic.adapters.PorudzbinaSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.example.taverna.elastic.adapters.PorudzbinaSearchAdapter.ViewHolder holder, int position) {
        PorudzbinaESDTO porucivanjeDTO = mData.get(position);
        holder.komentar.setText(porucivanjeDTO.getKomentar());
        holder.satnica.setText(porucivanjeDTO.getSatnica());

        if(porucivanjeDTO.getAnoniman_komentar()=="true"){
            holder.anoniman.setText("Anoniman komentar");
        }

        holder.anoniman.setText("Nije anominan komentar");



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

